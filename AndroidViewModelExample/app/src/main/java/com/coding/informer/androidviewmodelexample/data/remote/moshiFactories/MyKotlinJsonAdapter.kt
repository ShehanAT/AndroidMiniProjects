package com.coding.informer.androidviewmodelexample.data.remote.moshiFactories

import com.squareup.moshi.*
import com.squareup.moshi.internal.Util
import com.squareup.moshi.internal.Util.generatedAdapter
import com.squareup.moshi.internal.Util.resolve
import java.lang.reflect.Modifier
import java.lang.reflect.Type
import kotlin.reflect.KFunction
import kotlin.reflect.KMutableProperty1
import kotlin.reflect.KParameter
import kotlin.reflect.KProperty1
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

/**
 * Classes annotated with this are eligible for this adapter
 */
private val KOTLIN_METADATA = Class.forName("kotlin.Metadata") as Class<out Annotation>


/**
 * Placeholder value used when a field is absent from the JSON. Note that this code
 * distinguishes between absent values and present-but-null values.
 */
private val ABSENT_VALUE = Any()

/**
 * This class encodes Kotlin classes using their properties. It decodes them by first invoking the
 * constructor, and then by setting any additional properties that exist, if any
 */
internal class MyKotlinJsonAdapter<T>(
    val constructor: KFunction<T>,
    val bindings: List<Binding<T, Any?>?>,
    val options: JsonReader.Options): JsonAdapter<T>(){
    data class Binding<K, P>(
        val name: String,
        val adapter: JsonAdapter<P>,
        val property: KProperty1<K, P>,
        val parameter: KParameter?){
        fun get(value: K) = property.get(value)

        fun set(result: K, value: P){
            if(value != ABSENT_VALUE){
                (property as KMutableProperty1<K, P>).set(result, value)
            }
        }
    }

    override fun fromJson(reader: JsonReader): T? {
        TODO("Not yet implemented")
    }

    override fun toJson(writer: JsonWriter, value: T?) {
        TODO("Not yet implemented")
    }
}

class MyKotlinJsonAdapterFactory: JsonAdapter.Factory {

    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        if(annotations.isNotEmpty()) return null

        val rawType = Types.getRawType(type)
        if(rawType.isInterface) return null
        if(rawType.isEnum) return null
        if(!rawType.isAnnotationPresent(KOTLIN_METADATA)) return null
        if(Util.isPlatformType((rawType))) return null

        try {
            val generatedAdapter = generatedAdapter(moshi, type, rawType)
            if(generatedAdapter != null){
                return generatedAdapter
            }
        }catch(e: RuntimeException){
            if(e.cause !is ClassNotFoundException){
                throw e
            }
        }

        if(rawType.isLocalClass){
            throw IllegalArgumentException("Cannot serialize local class or object expression ${rawType.name}")
        }
        val rawTypeKotlin = rawType.kotlin
        if(rawTypeKotlin.isAbstract){
            throw IllegalArgumentException("Cannot serialize abstract class ${rawType.name}")
        }
        if(rawTypeKotlin.isInner){
            throw IllegalArgumentException("Cannot serialize inner class ${rawType.name}")
        }
        if(rawTypeKotlin.objectInstance != null){
            throw IllegalArgumentException("Cannot serialize object declaration ${rawType.name}")
        }

        val constructor = rawTypeKotlin.primaryConstructor ?: return null
        val parametersByName = constructor.parameters.associateBy { it.name }
        constructor.isAccessible = true

        val bindingsByName = LinkedHashMap<String, MyKotlinJsonAdapter.Binding<Any, Any?>>()

        for(property in rawTypeKotlin.memberProperties){
            val parameter = parametersByName[property.name]

            if(Modifier.isTransient(property.javaField?.modifiers ?: 0)){
                if(parameter != null && !parameter.isOptional){
                    throw IllegalArgumentException(
                        "No default value for transient constructor $parameter"
                    )
                    continue
                }
            }

            if(parameter != null && parameter.type != property.returnType){
                throw IllegalArgumentException("'#{property.name}' has a constructor parameter of type " +
                        "${parameter.type} but a property of type ${property.returnType}."
                )
            }

            if(property !is KMutableProperty1 && parameter == null) continue

            property.isAccessible = true
            var allAnnotations = property.annotations
            var jsonAnnotation = property.findAnnotation<Json>()

            if(parameter != null){
                allAnnotations += parameter.annotations
                if(jsonAnnotation == null){
                    jsonAnnotation = parameter.findAnnotation<Json>()
                }
            }

            val name = jsonAnnotation?.name ?: property.name
            val resolvedPropertyType = resolve(type, rawType, property.returnType.javaType)
            val adapter = moshi.adapter<Any>(
                resolvedPropertyType, Util.jsonAnnotations(allAnnotations.toTypedArray()), property.name
            )

            bindingsByName[property.name] =
                MyKotlinJsonAdapter.Binding(
                    name, adapter,
                    property as KProperty1<Any, Any?>, parameter
                )
        }

        val bindings = ArrayList<MyKotlinJsonAdapter.Binding<Any, Any?>?>()

        for(parameter in constructor.parameters){
            val binding = bindingsByName.remove(parameter.name)
            if(binding == null && !parameter.isOptional){
                throw IllegalArgumentException("No property for required constructor $parameter")
            }
            bindings += binding
        }

        bindings += bindingsByName.values

        val options = JsonReader.Options.of(*bindings.map { it?.name ?: "\u0000" }.toTypedArray())
        return MyKotlinJsonAdapter(
            constructor,
            bindings,
            options
        ).nullSafe()
    }
}