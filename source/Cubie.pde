class Cubie{
  PMatrix3D matrix;
  //Ejes a trabajar
  int x = 0;
  int y = 0;
  int z = 0;
  color c;
  Face [] faces = new Face[6];
  
  Cubie(PMatrix3D m, int x, int y, int z){
    this.matrix = m;
    this.x = x;
    this.y = y;
    this.z = z;
    c=color(255);
    //Todas las caras del cubo
    //Face and Color
    faces[0] = new Face(new PVector (0, 0, -1), color(0, 0, 255));
    faces[1] = new Face(new PVector (0, 0,  1), color(0, 255, 0));
    faces[2] = new Face(new PVector (0, 1,  0), color(255, 255, 255));
    faces[3] = new Face(new PVector (0, -1, 0), color(255,255, 0));
    faces[4] = new Face(new PVector (1, 0,  0), color(255, 150, 0));
    faces[5] = new Face(new PVector (-1, 0, 0), color(255, 0, 0));  
  }
  
  void turnFacesZ(int dir){//giro cara en z
    for (Face f : faces){
      f.turnZ(dir*HALF_PI);
    }
  }
  
  void turnFacesY(int dir){//giro cara en Y
    for (Face f : faces){
      f.turnY(dir*HALF_PI);
    }
  }
  
  void turnFacesX(int dir){//giro cara en x
    for (Face f : faces){
      f.turnX(dir*HALF_PI);
    }
  }
  
  void update(int x, int y, int z){//Actualizacion de los valores
    matrix.reset();
    matrix.translate(x, y, z);
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  void show(){
    //fill(c);
    noFill();
    stroke(0);
    strokeWeight(0.1);
    pushMatrix();
    applyMatrix(matrix);
    box(1);
    for (Face f : faces){
      f.show();
    }
    popMatrix();
  }
  
    
}
