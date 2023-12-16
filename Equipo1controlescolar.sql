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

------------------------------------------------------------------------------------------------------------------------------------
------------------------------Stored Procedure Materia-------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE MateriaGetAll (
resultado OUT SYS_REFCURSOR)
IS 
BEGIN
    OPEN resultado FOR
    SELECT Idmateria,Nombre,Costo FROM Materia;
END;


SET SERVEROUTPUT ON;
DECLARE 
resultado_cursor SYS_REFCURSOR;
id_materia NUMBER;
nombre VARCHAR (50);
Costo NUMBER(6,2);
BEGIN
MateriaGetAll(resultado_cursor);
LOOP
    FETCH resultado_cursor INTO id_materia,nombre,costo;
    EXIT WHEN resultado_cursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('ID:' || id_materia || ' -- Nombre:' || nombre || ' -- Costo:' || Costo);
END LOOP;
CLOSE resultado_cursor;
END;

-------------------------------------------------------------------------------------------------------------------

create or replace NONEDITIONABLE PROCEDURE MateriaGetById(
id_materiaa IN NUMBER,
resultado  IN OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN resultado FOR
    SELECT idmateria,nombre,costo FROM Materia
    WHERE id_materiaa = idmateria;
END;

SET SERVEROUTPUT ON;
DECLARE 
resultado_cursor SYS_REFCURSOR;
id_materia NUMBER;
nombre VARCHAR (50);
costo NUMBER(6,2);
BEGIN
MateriaGetById(1,resultado_cursor);

LOOP
    FETCH resultado_cursor INTO id_materia,nombre,costo;
    EXIT WHEN resultado_cursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('ID:' || id_materia || ' -- Nombre:' || nombre || ' -- Costo:' || costo);
END LOOP;
CLOSE resultado_cursor;
END;

------------------------------------------------------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE MateriaAdd
(
nombre in varchar,
costo in number
)
IS
BEGIN
Insert Into Materia(Nombre,Costo) 
    Values (nombre,costo);
END;

BEGIN
MateriaAdd('Logica de Programación',2550.50);
END;

SELECT * FROM Materia;


---------------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE NONEDITIONABLE PROCEDURE MateriaUpdate
(
idmateriaa in NUMBER,
nombree in VARCHAR,
costoo in NUMBER
)
IS
BEGIN
Update Materia set  Nombre = nombree, 
                   Costo = costoo
                    WHERE Idmateria = idmateriaa;

END;

BEGIN
MateriaUpdate(1,'Redes Neuronales',1500.5);
END;

SELECT * FROM Materia;

-----------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE MateriaDelete
(
idmateriaa in NUMBER
)
IS
BEGIN
Delete From Materia Where Idmateria = idmateriaa;
END;

BEGIN
MateriaDelete(2);
END;
SELECT * FROM Materia;



-------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------
-----------------------Stored procedure MAteriaAlumno-----------------------------------------------


CREATE OR REPLACE NONEDITIONABLE PROCEDURE AlumMatGetAll (
resultado OUT SYS_REFCURSOR)
IS 
BEGIN
    OPEN resultado FOR
    SELECT idalumnomateria,idalumno,idmateria FROM AlumnoMateria;
END;


SET SERVEROUTPUT ON;
DECLARE 
resultado_cursor SYS_REFCURSOR;
id_alumnomateria NUMBER;
id_alumno NUMBER;
id_materia NUMBER;
BEGIN
AlumMatGetAll(resultado_cursor);
LOOP
    FETCH resultado_cursor INTO id_alumnomateria,id_alumno,id_materia;
    EXIT WHEN resultado_cursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('ID:' || id_alumnomateria || ' --ID Alumno:' || id_alumno || ' -- ID Materia:' || id_materia);
END LOOP;
CLOSE resultado_cursor;
END;

----------------------------------------------------------------------------------------------------------------
create or replace NONEDITIONABLE PROCEDURE AlumMatGetById(
id_alumnomateriaa IN NUMBER,
resultado  IN OUT SYS_REFCURSOR
)
IS
BEGIN
    OPEN resultado FOR
    SELECT idalumnomateria,idalumno,idmateria FROM AlumnoMateria
    WHERE id_alumnomateriaa = idalumnomateria;
END;

SET SERVEROUTPUT ON;
DECLARE 
resultado_cursor SYS_REFCURSOR;
id_alumnomateria NUMBER;
id_alumno NUMBER;
id_materia NUMBER;
BEGIN
AlumMatGetById(4,resultado_cursor);

LOOP
    FETCH resultado_cursor INTO id_alumnomateria,id_alumno,id_materia;
    EXIT WHEN resultado_cursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE('ID:' || id_alumnomateria || ' --ID Alumno:' || id_alumno || ' -- ID Materia:' || id_materia);
END LOOP;
CLOSE resultado_cursor;
END;

-------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE AlumMatAdd
(
idalumno in NUMBER,
idmateria in NUMBER
)
IS
BEGIN
Insert Into AlumnoMateria(Idalumno,Idmateria) 
    Values (idalumno,idmateria);
END;

BEGIN
AlumMatAdd(1,3);
END;

SELECT * FROM AlumnoMateria;

---------------------------------------------------------------------------------------------

Create or replace NONEDITIONABLE PROCEDURE AlumMatUpdate
(
idalumnomateriaa NUMBER,
idalumnoo in NUMBER,
idmateriaa in NUMBER
)
IS
BEGIN
Update AlumnoMateria set    Idalumno = idalumnoo, 
                            Idmateria = idmateriaa
                            WHERE Idalumnomateria = idalumnomateriaa;
END;

BEGIN
AlumMatUpdate(1,2,3);
END;

SELECT * FROM AlumnoMateria;

------------------------------------------------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE AlumMatDelete
(
idalumnomateriaa in NUMBER
)
IS
BEGIN
Delete From AlumnoMateria Where Idalumnomateria = idalumnomateriaa;
END;

BEGIN
AlumMatDelete(4);
END;

SELECT * FROM AlumnoMateria;







