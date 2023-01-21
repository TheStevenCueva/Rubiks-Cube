import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import peasy.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class RubicksCube extends PApplet {



PeasyCam cam;
//dimension del cubo 3x3x3
int dim= 3;
Cubie [] cube = new Cubie[dim*dim*dim];

//Todos los movimientos que se puede realizar con el cubo
String [] allMoves = {"f", "b", "u", "d", "r", "l"};
//movimientos aleatorios
String sequence = "";
//movimientos realizados
String sequenceRevert = "";
int counter = 0;
int counter2 = 0;
//variables de control
boolean started = false;
boolean started2 = false;


//Pantalla
public void setup() {
  //Tamaño de la pantalla
  //size(900, 700, P3D);
  
  cam= new PeasyCam(this, 400);
  int index= 0;
  for (int x = -1; x <= 1; x++) {
    for (int y = -1; y <= 1; y++) {
      for (int z = -1; z <= 1; z++) {
        PMatrix3D matrix = new PMatrix3D();
        matrix.translate(x, y, z);
        cube [index] = new Cubie(matrix, x, y, z);
        index++;
      }
    }
  }
  //Movimientos aleatorios para revolver el cubo
  for (int i = 0; i < 10; i++) {
    int r = PApplet.parseInt(random(allMoves.length));
    if (random(1) < 0.5f) {
      sequence += allMoves[r];
    } else {
      sequence += allMoves[r].toUpperCase();
    }
  }
}

//Metodo: Revertir movimientos realizados
public String flipCase(char c) {
  String s = "" + c;
  if (s.equals(s.toLowerCase())) {
    return s.toUpperCase();
  } else {
    return s.toLowerCase();
  }
}

//Dibujo de la figura
public void draw() {
  //Fondo
  background(51);
  
  //Ayudas
  cam.beginHUD();
  fill(255);
  textSize(20);
  text("References:",390,50);
  text("Up:yelow",500,50);
  text("Down:White",500,70);
  text("Rigth:orange",500,90);
  text("Left:Red",500,110);
  text("Back:Blue",500,130);
  text("Front:Green",500,150);
  
  text("Center:c",70,400);
  //text("Turn:t",70,420);
  text("Solve:s",70,440);
  
  textSize(20);
  text("====<Controles/Notaciones>====",70,480);
  text("(R:horario / r: ANTI-horario)=> DERECHA",70,510);
  text("(L: ANTI-horario / l: horario)=> IZQUIERDA",70,555);
  text("(D: ANTI-horario / d: horario)=> ABAJO",70,600); 
  text("(B: ANTI-horario / b: horario)=> ATRAS",70,645);
  text("(F: horario / f: ANTI-horario)=> FRENTE",70,690);
  text("(U: horario / u: ANTI-horario)=> ARRIBA",70,735);
  
  textSize(25);
  text("Moves:",70,70);
  textSize(15);
  text(counter, 100, 100);
  cam.endHUD();  
  
  //Inicio de la figura con un angulo
  rotateX(-0.5f);
  rotateY(0.4f);
  rotateZ(0.1f);
  
  //Revolver el cubo
  if (started) {
    if (frameCount % 10 == 0) {
      if (counter < sequence.length()) {
        char move = sequence.charAt(counter);
        applyMove(move);
        counter++;
      }
    }
  }
  
  //Resolver el cubo
  if (started2) {
    if (frameCount % 0.5f == 0) {
      if (counter2 < sequenceRevert.length()) {
        char move2 = sequenceRevert.charAt(counter2);
        applyMove(move2);
        counter2++;
      }
    }
  }
  
  //Tamaño de la Figura
  scale(50);
  for (int i = 0; i < cube.length; i++) {
    //Presetacion de la Figura
    cube[i].show();
  }
}
class Cubie{
  PMatrix3D matrix;
  //Ejes a trabajar
  int x = 0;
  int y = 0;
  int z = 0;
  int c;
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
  
  public void turnFacesZ(int dir){//giro cara en z
    for (Face f : faces){
      f.turnZ(dir*HALF_PI);
    }
  }
  
  public void turnFacesY(int dir){//giro cara en Y
    for (Face f : faces){
      f.turnY(dir*HALF_PI);
    }
  }
  
  public void turnFacesX(int dir){//giro cara en x
    for (Face f : faces){
      f.turnX(dir*HALF_PI);
    }
  }
  
  public void update(int x, int y, int z){//Actualizacion de los valores
    matrix.reset();
    matrix.translate(x, y, z);
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public void show(){
    //fill(c);
    noFill();
    stroke(0);
    strokeWeight(0.1f);
    pushMatrix();
    applyMatrix(matrix);
    box(1);
    for (Face f : faces){
      f.show();
    }
    popMatrix();
  }
  
    
}
//Controles al presionar una Tecla
public void keyPressed() {
  switch (key) {
    //back
  case 'B'://horario
    turnZ(-1, 1);
    counter++;
    counter2++;
    sequenceRevert += "B";
    //printArray(sequenceRevert);
    //println(sequenceRevert.length());
    break;
  case 'b'://anti-horario
    turnZ(-1, -1);
    counter++;
    counter2++;
    sequenceRevert += "b";
    break; 

    //front
  case 'F'://horario
    turnZ(1, 1);
    counter++;
    counter2++;
    sequenceRevert += "F";
    break;
  case 'f'://anti-horario
    turnZ(1, -1);
    counter++;
    counter2++;
    sequenceRevert += "f";
    break;

    //up 
  case 'U'://horario
    turnY(-1, 1);
    counter++;
    counter2++;
    sequenceRevert += "U";
    break;
  case 'u'://anti-horario
    turnY(-1, -1);
    counter++;
    counter2++;
    sequenceRevert += "u";
    break;  

    //down
  case 'D'://horario
    turnY(1, 1);
    counter++;
    counter2++;
    sequenceRevert += "D";
    break;
  case 'd'://anti-horario
    turnY(1, -1);
    counter++;
    counter2++;
    sequenceRevert += "d";
    break;

    //left
  case 'L'://horario
    turnX(-1, 1);
    counter++;
    counter2++;
    sequenceRevert += "L";
    break;
  case 'l'://anti-horario
    turnX(-1, -1);
    counter++;
    counter2++;
    sequenceRevert += "l";
    break;

    //rigth
  case 'R'://horario
    turnX(1, 1);
    counter++;
    counter2++;
    sequenceRevert += "R";
    break;
  case 'r'://anti-horario
    turnX(1, -1);
    counter++;
    counter2++;
    sequenceRevert += "r";
    break;

    //Sequencias
  case 't'://revolver
    started=true;
    break;
    //Sequencias
  case 'S' | 's'://resolver
    for (int i = sequenceRevert.length()-1; i >= 0; i--) {
      String nextMove = flipCase(sequenceRevert.charAt(i));//nextMove
      sequenceRevert += nextMove;
    }
    started2=true;
    break;
    //camara centrar
  case 'c':// centrar
    cam.reset();
    break;
  }
  if (keyCode==142) {//dividir=U
    turnY(-1, 1);
    counter++;
    counter2++;
    sequenceRevert += "U";
  }
  if (keyCode==141) {//por*=u
    turnY(-1, -1);
    counter++;
    counter2++;
    sequenceRevert += "u";
  }
  if (keyCode==136) {//por*=l
    turnX(-1, 1);
    counter++;
    counter2++;
    sequenceRevert += "L";
  }
  if (keyCode==133) {//por*=l
    turnX(-1, -1);
    counter++;
    counter2++;
    sequenceRevert += "l";
  }
  
  if (keyCode==137) {//por*=R
    turnX(1, 1);
    counter++;
    counter2++;
    sequenceRevert += "R";
  }
  if (keyCode==134) {//por*=r
    turnX(1, -1);
    counter++;
    counter2++;
    sequenceRevert += "r";
  }
  
  if (keyCode==130) {//por*=D
    turnY(1, 1);
    counter++;
    counter2++;
    sequenceRevert += "D";
  }
  if (keyCode==131) {//por*=d
    turnY(1, -1);
    counter++;
    counter2++;
    sequenceRevert += "d";
  }
  
  if (keyCode==135) {//por*=B 
    turnZ(-1, 1);
    counter++;
    counter2++;
    sequenceRevert += "B";
  }
  if (keyCode==132) {//por*=b  
    turnZ(-1, -1);
    counter++;
    counter2++;
    sequenceRevert += "b";
  }
  
  if (keyCode==129) {//por*=F
    turnZ(1, 1);
    counter++;
    counter2++;
    sequenceRevert += "F";
  }
  if (keyCode==128) {//por*=f  
    turnZ(1, -1);
    counter++;
    counter2++;
    sequenceRevert += "f";
  }
  //println("keyprese:"+keyCode);
}

//Aplicar movimientos existentes para revolver y resolver el cubo
public void applyMove(char move) {
  switch (move) {
    //back
  case 'B'://horario
    turnZ(-1, 1);
    break;
  case 'b'://anti-horario
    turnZ(-1, -1);
    break; 

    //front
  case 'F'://horario
    turnZ(1, 1);
    break;
  case 'f'://anti-horario
    turnZ(1, -1);
    break;

    //up 
  case 'U'://horario
    turnY(-1, 1);
    break;
  case 'u'://anti-horario
    turnY(-1, -1);
    break;  

    //down
  case 'D'://horario
    turnY(1, 1);
    break;
  case 'd'://anti-horario
    turnY(1, -1);
    break;

    //left
  case 'L'://horario
    turnX(-1, 1);
    break;
  case 'l'://anti-horario
    turnX(-1, -1);
    break;

    //rigth
  case 'R'://horario
    turnX(1, 1);
    break;
  case 'r'://anti-horario
    turnX(1, -1);
    break;
  }
}
class Face{
  PVector normal;//Recibe cordenadas x, y, z
  int c;
  Face(PVector normal, int c){
   this.normal = normal;
   this.c = c;
  }
  
  public void turnZ(float angle){//giro en z
   PVector v2 = new PVector();
   v2.x = round(normal.x * cos(angle) - normal.y * sin(angle));//coordenada x
   v2.y = round(normal.x * sin(angle) - normal.y * cos(angle));//coordenada y
   v2.z = round(normal.z);//coordenada z
   normal = v2;
  }
   
  public void turnY(float angle){//giro en y
   PVector v2 = new PVector();
   v2.x = round(normal.x * cos(angle) - normal.z * sin(angle));//coordenada x
   v2.z = round(normal.x * sin(angle) - normal.z * cos(angle));//coordenada z
   v2.y = round(normal.y);//coordenada y
   normal = v2;
  }
   
  public void turnX(float angle){//giro en x
   PVector v2 = new PVector();
   v2.y = round(normal.y * cos(angle) - normal.z * sin(angle));//coordenada y
   v2.z = round(normal.y * sin(angle) - normal.z * cos(angle));//coordenada z
   v2.x = round(normal.x);//coordenada x
   normal = v2;
  }
   
  public void show(){//Presentacion de la matriz
   pushMatrix();
   //color de la cara
   fill(c);
   noStroke();
   rectMode(CENTER);
   translate(0.5f*normal.x, 0.5f*normal.y, 0.5f*normal.z);
 
   if(abs(normal.x) > 0){
     rotateY(HALF_PI);
   }else if(abs(normal.y) > 0){
     rotateX(HALF_PI);
   }
   square(0, 0, 1);
   popMatrix();
  }
}
//Giros de los ejes

public void turnZ(int index, int dir){//Giro eje Z
  for(int i = 0; i< cube.length; i++){//Recorrido del cubo
    Cubie qb = cube[i]; 
    if (qb.z==index){
      PMatrix2D matrix = new PMatrix2D();
      matrix.rotate(dir*HALF_PI);//angulo de giro 
      matrix.translate(qb.x, qb.y);
      qb.turnFacesZ(dir);//Direccion de giro
      qb.update(round(matrix.m02), round(matrix.m12), round(qb.z));//actualizacion de los datos al girar
    }
  }
}

public void turnY(int index, int dir){//Giro eje Y
  for(int i = 0; i< cube.length; i++){//Recorrido del cubo
    Cubie qb = cube[i];
    if (qb.y==index){
      PMatrix2D matrix = new PMatrix2D();
      matrix.rotate(dir*HALF_PI);//angulo de giro 
      matrix.translate(qb.x, qb.z);
      qb.turnFacesY(dir);//Direccion de giro
      qb.update(round(matrix.m02), qb.y, round(matrix.m12));//actualizacion de los datos al girar
    }
  }
}


public void turnX(int index, int dir){//Giro eje X
  for(int i = 0; i< cube.length; i++){//Recorrido del cubo
    Cubie qb = cube[i];
    if (qb.x==index){
      PMatrix2D matrix = new PMatrix2D();
      matrix.rotate(dir*HALF_PI);//angulo de giro 
      matrix.translate(qb.y, qb.z);
      qb.turnFacesX(dir);//Direccion de giro
      qb.update(qb.x, round(matrix.m02), round(matrix.m12));//actualizacion de los datos al girar
    }
  }
}
  public void settings() {  fullScreen(P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "RubicksCube" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
