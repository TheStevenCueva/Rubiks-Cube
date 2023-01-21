//Giros de los ejes

void turnZ(int index, int dir){//Giro eje Z
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

void turnY(int index, int dir){//Giro eje Y
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


void turnX(int index, int dir){//Giro eje X
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
