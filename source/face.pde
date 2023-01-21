class Face{
  PVector normal;//Recibe cordenadas x, y, z
  color c;
  Face(PVector normal, color c){
   this.normal = normal;
   this.c = c;
  }
  
  void turnZ(float angle){//giro en z
   PVector v2 = new PVector();
   v2.x = round(normal.x * cos(angle) - normal.y * sin(angle));//coordenada x
   v2.y = round(normal.x * sin(angle) - normal.y * cos(angle));//coordenada y
   v2.z = round(normal.z);//coordenada z
   normal = v2;
  }
   
  void turnY(float angle){//giro en y
   PVector v2 = new PVector();
   v2.x = round(normal.x * cos(angle) - normal.z * sin(angle));//coordenada x
   v2.z = round(normal.x * sin(angle) - normal.z * cos(angle));//coordenada z
   v2.y = round(normal.y);//coordenada y
   normal = v2;
  }
   
  void turnX(float angle){//giro en x
   PVector v2 = new PVector();
   v2.y = round(normal.y * cos(angle) - normal.z * sin(angle));//coordenada y
   v2.z = round(normal.y * sin(angle) - normal.z * cos(angle));//coordenada z
   v2.x = round(normal.x);//coordenada x
   normal = v2;
  }
   
  void show(){//Presentacion de la matriz
   pushMatrix();
   //color de la cara
   fill(c);
   noStroke();
   rectMode(CENTER);
   translate(0.5*normal.x, 0.5*normal.y, 0.5*normal.z);
 
   if(abs(normal.x) > 0){
     rotateY(HALF_PI);
   }else if(abs(normal.y) > 0){
     rotateX(HALF_PI);
   }
   square(0, 0, 1);
   popMatrix();
  }
}
