//Controles al presionar una Tecla
void keyPressed() {
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
void applyMove(char move) {
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
