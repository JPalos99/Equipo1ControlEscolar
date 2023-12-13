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
--------------------------------

--------------------------------
--------------------------------
--------Stored Procedures Alumno-------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE AlumnoGetAll (
resultado OUT SYS_REFCURSOR)
IS 
BEGIN
    OPEN resultado FOR
    SELECT idalumno,nombre,apellidopaterno,apellidomaterno FROM Alumno;
END;


SET SERVEROUTPUT ON;
DECLARE 
resultado_cursor SYS_REFCURSOR;
id_alumno NUMBER;
nombre_alumno VARCHAR (50);
apellido_paterno VARCHAR (50);
apellido_materno VARCHAR(50);
BEGIN
AlumnoGetAll(resultado_cursor);
LOOP
    FETCH resultado_cursor INTO id_alumno,nombre_alumno,apellido_paterno,apellido_materno;
    EXIT WHEN resultado_cursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('ID:' || id_alumno || ' -- Nombre:' || nombre_alumno || ' -- ApellidoPaterno:' || apellido_paterno || ' -- ApellidoMaterno:' || apellido_materno);
END LOOP;
CLOSE resultado_cursor;
END;

---------------------------------------------------------------------------------------------------------------------------------------------

create or replace NONEDITIONABLE PROCEDURE AlumnoGetById(
id_alumnoo IN NUMBER,
resultado  IN OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN resultado FOR
    SELECT idalumno,nombre,apellidopaterno,apellidomaterno FROM Alumno
    WHERE id_alumnoo = idalumno;
END;

SET SERVEROUTPUT ON;
DECLARE 
resultado_cursor SYS_REFCURSOR;
id_alumno NUMBER;
nombre_alumno VARCHAR (50);
apellido_paterno VARCHAR (50);
apellido_materno VARCHAR(50);
BEGIN
AlumnoGetById(2,resultado_cursor);

LOOP
    FETCH resultado_cursor INTO id_alumno,nombre_alumno,apellido_paterno,apellido_materno;
    EXIT WHEN resultado_cursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('ID:' || id_alumno || ' -- Nombre:' || nombre_alumno || ' -- ApellidoPaterno:' || apellido_paterno || ' -- ApellidoMaterno:' || apellido_materno);
END LOOP;
CLOSE resultado_cursor;
END;

-----------------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE AlumnoAdd
(
nombre in varchar,
apellidoPaterno in varchar,
apellidoMaterno in varchar 
)
IS
BEGIN
Insert Into Alumno(Nombre,ApellidoPaterno,ApellidoMaterno) 
    Values (nombre,apellidoPaterno ,apellidoMaterno);
END;

BEGIN
AlumnoAdd('Carlos','Rivera','Rodriguez');
END;

SELECT * FROM Alumno;


---------------------------------------------------------------------------------------------------------------------------------------------------
Create or replace NONEDITIONABLE PROCEDURE AlumnoUpdate
(
idalumnoo in NUMBER,
nombreParametro in VARCHAR,
apellidoPa in VARCHAR,
apellidoMa in VARCHAR
)
IS
BEGIN
Update Alumno set  Nombre = nombreParametro, 
                    Apellidopaterno = apellidoPa,
                    Apellidomaterno = apellidoMa
                    WHERE Idalumno = idalumnoo;

END;

BEGIN
AlumnoUpdate(3,'Fernanda','Flores','Escutia');
END;

SELECT * FROM Alumno;

-----------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE AlumnoDelete
(
idalumnoo in NUMBER
)
IS
BEGIN
Delete From Alumno Where Idalumno = idalumnoo;
END;

BEGIN
AlumnoDelete(3);
END;
SELECT * FROM Alumno;

--------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------








