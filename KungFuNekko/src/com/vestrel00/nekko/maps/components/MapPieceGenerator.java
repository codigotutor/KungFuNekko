package com.vestrel00.nekko.maps.components;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.vestrel00.nekko.KFNekko;

public class MapPieceGenerator {
	
	public static final int BRIDGE_1 = 0;
	
	private static AtlasRegion[] tiles;
	
	public static void init(){
		if(tiles == null){
		tiles = new AtlasRegion[14];
		for(int i=0; i<tiles.length;i++)
			tiles[i] = KFNekko.resource.atlas.findRegion("piece"+String.valueOf(i));
	
		}
	}
	
	
	public static MapPiece getPiece(int pieceId, float offsetX, float offsetY){
		MapPiece piece = new MapPiece(512.0f, 232.0f); 
		
		piece.horizontal = new float[9];
		
		// horizontal platform 1
		piece.horizontal[0] = -5.0f;
		piece.horizontal[1] = 64.0f;
		piece.horizontal[2] = 110.0f;
		
		// horizontal platform 2
		piece.horizontal[3] = 150.0f;
		piece.horizontal[4] = 128.0f;
		piece.horizontal[5] = 361.0f;

		// horizontal platform 3
		piece.horizontal[6] = 415.0f;
		piece.horizontal[7] = 64.0f;
		piece.horizontal[8] = 517.0f;
		
		
		piece.slope = new float[8];
		
		// slope platform 1
		piece.slope[0] = 90.0f;
		piece.slope[1] = 70.0f;
		piece.slope[2] = 160.0f;
		piece.slope[3] = 132.0f;
		
		// slope platform 2
		piece.slope[4] = 351.0f;
		piece.slope[5] = 132.0f;
		piece.slope[6] = 416.0f;
		piece.slope[7] = 70.0f;
		
		piece.regions = new AtlasRegion[16];
		piece.regionXCoords = new float[piece.regions.length];
		piece.regionYCoords = new float[piece.regions.length];
		
		// piece 1s (6 count)
		int i=0, j=0;
		for(i=0; i<6 ; i++){
			piece.regions[i] = tiles[0];
			piece.regionYCoords[i] = 0.0f;
			
		}
		
		for(i=0, j=0; i<3; i++, j+=tiles[0].originalWidth)
			piece.regionXCoords[i] = (float)j;
		
		
		for(i=3, j=320; i<6; i++, j+=tiles[0].originalWidth)
			piece.regionXCoords[i] = (float)j;
		
		
		piece.regions[6] = tiles[10];
		piece.regions[7] = tiles[11];
		piece.regionXCoords[6] = 6.0f;
		piece.regionYCoords[6] = 64.0f;
		piece.regionXCoords[7] = 447.0f;
		piece.regionYCoords[7] = 64.0f;
		
			
		piece.regions[11] = tiles[13];
		piece.regions[12] = tiles[12];
		piece.regionXCoords[11] = 64.0f;
		piece.regionYCoords[11] = 64.0f;
		piece.regionXCoords[12] = 416.0f;
		piece.regionYCoords[12] = 64.0f;		
		

		piece.regions[8] = tiles[4];
		piece.regionXCoords[8] = 96.0f;
		piece.regionYCoords[8] = 64.0f;
		piece.regions[9] = tiles[3];
		piece.regionXCoords[9] = 352.0f;
		piece.regionYCoords[9] = 64.0f;		
		
		
		 piece.regions[10] = tiles[1];
		 piece.regionXCoords[10] = 160.0f;
		 piece.regionYCoords[10] = 64.0f;
		 
		 piece.regions[13] = tiles[9];
		 piece.regionXCoords[13] = 160.0f;
		 piece.regionYCoords[13] = 128.0f;
		 
		 piece.regions[14] = tiles[9];
		 piece.regionXCoords[14] = 224.0f;
		 piece.regionYCoords[14] = 128.0f;
		 
		 piece.regions[15] = tiles[9];
		 piece.regionXCoords[15] = 288.0f;
		 piece.regionYCoords[15] = 128.0f;
		
		piece.translate(offsetX, offsetY);
		return piece;
	}

}
