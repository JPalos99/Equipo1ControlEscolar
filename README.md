--Base de datos
CREATE TABLE Alumno(
Idalumno NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Nombre VARCHAR(50),
Apellidopaterno VARCHAR(50),
Apellidomaterno VARCHAR(50)
)
