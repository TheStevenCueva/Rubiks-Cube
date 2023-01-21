import peasy.*;

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
void setup() {
  //Tamaño de la pantalla
  //size(900, 700, P3D);
  fullScreen(P3D);
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
    int r = int(random(allMoves.length));
    if (random(1) < 0.5) {
      sequence += allMoves[r];
    } else {
      sequence += allMoves[r].toUpperCase();
    }
  }
}

//Metodo: Revertir movimientos realizados
String flipCase(char c) {
  String s = "" + c;
  if (s.equals(s.toLowerCase())) {
    return s.toUpperCase();
  } else {
    return s.toLowerCase();
  }
}

//Dibujo de la figura
void draw() {
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
  rotateX(-0.5);
  rotateY(0.4);
  rotateZ(0.1);
  
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
    if (frameCount % 0.5 == 0) {
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
