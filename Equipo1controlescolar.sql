CREATE TABLE Alumno(
Idalumno NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50),
Apellidopaterno VARCHAR(50),
Apellidomaterno VARCHAR(50)
)
SELECT * FROM alumno; 
INSERT INTO Alumno (Nombre,Apellidopaterno,Apellidomaterno) VALUES ('Pablo','Juarez','Dominguez');

CREATE TABLE Materia(
Idmateria NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50),
Costo NUMBER(6,2)
)
SELECT * FROM Materia;
INSERT INTO Materia (Nombre,Costo) VALUES ('Fundamento de la programación',2500.00);

DROP TABLE Materia;

CREATE TABLE AlumnoMateria(
Idalumno NUMBER,
Idmateria NUMBER,
CONSTRAINT fk_alumno_alumno FOREIGN KEY (Idalumno) REFERENCES Alumno(Idalumno),
CONSTRAINT fk_materia_materia FOREIGN KEY (Idmateria) REFERENCES Materia(Idmateria)
)

INSERT INTO AlumnoMateria (Idalumno,Idmateria) VALUES (1,1);
SELECT * FROM AlumnoMateria;


--Welcome01$$$@


--------------------------------

