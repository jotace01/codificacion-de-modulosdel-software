use diego_dj_producciones;

Drop table if exists Registro;

use diego_dj_producciones;

create table Registro(
codigo int auto_increment primary key,
Nombre varchar (50) not null,
Apellido varchar (50) not null,
Telefono varchar (11) not null,
Correo varchar (25) not null,
Tipo_Evento varchar (25),
Cantidad_Invitados int (99)
);

INSERT INTO Registro (Nombre, Apellido, Telefono, Correo, Tipo_Evento, Cantidad_Invitados)
VALUES ('julio', 'hernandez', 3195669281, 'yualvili27@gmail.com', 'cumpleaños', 50);


INSERT INTO Registro (Nombre, Apellido, Telefono, Correo, Tipo_Evento, Cantidad_Invitados)
VALUES ('diego', 'murillo', 3005764784, 'diegodj@gmail.com', 'quince años', 45);

INSERT INTO Registro (Nombre, Apellido, Telefono, Correo, Tipo_Evento, Cantidad_Invitados)
VALUES ('diana', 'mahecha', 3001234567, 'dianamahe@gmail.com', 'empresarial', 80),
('andres', 'bernal', 3005824652, 'felipe@gmail.com', 'cumpleaños', 30),
('stephania', 'hernandez', 3154687913, 'tefa@gmail.com', 'empresarial', 50);

INSERT INTO Registro (Nombre, Apellido, Telefono, Correo, Tipo_Evento, Cantidad_Invitados)
VALUES ('mario', 'hernandez', 3045381746, 'mario@gmail.com', 'cumpleaños', 50);