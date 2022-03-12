import 'package:flutter/material.dart';

typedef Widget GalleryWidgetBuilder();

/// Helper to build gallery.
class GalleryScaffold extends StatefulWidget {
  /// The widget used for leading in a [ListTile].
  final Widget listTileIcon;
  final String title;
  final String subtitle;
  final GalleryWidgetBuilder childBuilder;

  GalleryScaffold({
    required this.listTileIcon,
    required this.title,
    required this.subtitle,
    required this.childBuilder,
  });

  /// Gets the gallery
  Widget buildGalleryListTile(BuildContext context) => new ListTile(
      leading: listTileIcon,
      title: new Text(title),
      subtitle: new Text(subtitle),
      onTap: () {
        Navigator.push(context, new MaterialPageRoute(builder: (_) => this));
      });

  @override
  _GalleryScaffoldState createState() => new _GalleryScaffoldState();
}

class _GalleryScaffoldState extends State<GalleryScaffold> {
  void _handleButtonPress() {
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text(widget.title)),
      body: new Padding(
          padding: const EdgeInsets.all(8.0),
          child: new Column(children: <Widget>[
            new SizedBox(height: 250.0, child: widget.childBuilder()),
          ])),
      floatingActionButton: new FloatingActionButton(
          onPressed: _handleButtonPress, child: new Icon(Icons.refresh)),
    );
  }
}
