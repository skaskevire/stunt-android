
package com.stunt.util;

public interface MapObjectVisitor
{
	void visit(com.badlogic.gdx.maps.objects.RectangleMapObject rmo);

	void visit(com.badlogic.gdx.maps.objects.CircleMapObject cmo);

	void visit(com.badlogic.gdx.maps.objects.PolygonMapObject cmo);

	void visit(com.badlogic.gdx.maps.objects.EllipseMapObject cmo);

	void visit(com.badlogic.gdx.maps.objects.TextureMapObject cmo);
	
	void visit(com.badlogic.gdx.maps.objects.PolylineMapObject cmo);
}
