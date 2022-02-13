import 'package:flutter/cupertino.dart';
import 'package:flutter_colorpicker/flutter_colorpicker.dart';


class ColorPickerBuilder extends StatelessWidget {
  final Color color;
  final ValueChanged<Color> onColorChanged;

  ColorPickerBuild({ required this.color, required this.onColorChanged })

  @override 
  Widget build(BuildContext context){
    return ClipOval(
      child: Container(
        hieght: 32.0,
        width: 32.0,
        child: Material(
          color: color,
          child: InkWell(
            borderRadius: BorderRadius.circular(50.0),
            onTap: () {
              context: context,
              builder: (BuildContext context){
                return AlertDialog(
                  title: Text('Select a color '),
                  context: SingleChildScrollView(
                    child: BlockPicker(
                      availableColors: ColorUtils.defaultColors,
                      pickerColor: color,
                      onColorChanged: onColorChanged 
                    )
                  )
                )
              }
            }
          )
        )
      ))
    )
  }
}
