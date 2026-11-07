USE FairPlay;
GO

-- ==========================================
-- 1. ELIMINAR TABLAS VIEJAS
-- ==========================================
DROP TABLE IF EXISTS [dbo].[EquipoJugador];
DROP TABLE IF EXISTS [dbo].[JugadorHabilidad];
DROP TABLE IF EXISTS [dbo].[Equipo];
DROP TABLE IF EXISTS [dbo].[Partido];
DROP TABLE IF EXISTS [dbo].[Habilidad];
DROP TABLE IF EXISTS [dbo].[Jugador];
DROP TABLE IF EXISTS [dbo].[Posicion];
GO

-- ==========================================
-- 2. CREAR TABLAS PRINCIPALES
-- ==========================================
CREATE TABLE [dbo].[Posicion] (
                                  [idPosicion] INT IDENTITY(1,1) PRIMARY KEY,
                                  [nombrePosicion] VARCHAR(50) NULL,
                                  [descripcion] VARCHAR(255) NULL
);

CREATE TABLE [dbo].[Jugador] (
                                 [idjugador] INT IDENTITY(1,1) PRIMARY KEY,
                                 [nombre] VARCHAR(100) NOT NULL,
                                 [apellido] VARCHAR(100) NOT NULL,
                                 [fechaRegistro] DATE NULL,
                                 [idPosicion] INT NULL
);

CREATE TABLE [dbo].[Habilidad] (
                                   [idHabilidad] INT IDENTITY(1,1) PRIMARY KEY,
                                   [nombreHabilidad] VARCHAR(100) NOT NULL,
                                   [descripcion] VARCHAR(255) NULL
);

CREATE TABLE [dbo].[Partido] (
                                 [idPartido] INT IDENTITY(1,1) PRIMARY KEY,
                                 [fechaHora] DATETIME NOT NULL,
                                 [tipoPartido] VARCHAR(50) NOT NULL,
                                 [estado] VARCHAR(50) DEFAULT 'Programado' NULL
);

-- ==========================================
-- 3. CREAR TABLAS INTERMEDIAS Y DEPENDIENTES
-- ==========================================
CREATE TABLE [dbo].[JugadorHabilidad] (
                                          [idJugadorHabilidad] INT IDENTITY(1,1) PRIMARY KEY,
                                          [idJugador] INT NOT NULL,
                                          [idHabilidad] INT NOT NULL,
                                          [valor] INT NOT NULL
);

CREATE TABLE [dbo].[Equipo] (
                                [idEquipo] INT IDENTITY(1,1) PRIMARY KEY,
                                [nombreEquipo] VARCHAR(100) NOT NULL,
                                [idPartido] INT NOT NULL
);

CREATE TABLE [dbo].[EquipoJugador] (
                                       [idEquipoJugador] INT IDENTITY(1,1) PRIMARY KEY,
                                       [idEquipo] INT NOT NULL,
                                       [idJugador] INT NOT NULL
);
GO

-- ==========================================
-- 4. AGREGAR LLAVES FORÁNEAS
-- ==========================================
ALTER TABLE [dbo].[Jugador] WITH CHECK
    ADD CONSTRAINT [FK_Jugador_Posicion] FOREIGN KEY([idPosicion]) REFERENCES [dbo].[Posicion] ([idPosicion]);

ALTER TABLE [dbo].[JugadorHabilidad] WITH CHECK
    ADD CONSTRAINT [FK_JugadorHabilidad_Jugador] FOREIGN KEY([idJugador]) REFERENCES [dbo].[Jugador] ([idjugador]);

ALTER TABLE [dbo].[JugadorHabilidad] WITH CHECK
    ADD CONSTRAINT [FK_JugadorHabilidad_Habilidad] FOREIGN KEY([idHabilidad]) REFERENCES [dbo].[Habilidad] ([idHabilidad]);

ALTER TABLE [dbo].[Equipo] WITH CHECK
    ADD CONSTRAINT [FK_Equipo_Partido] FOREIGN KEY([idPartido]) REFERENCES [dbo].[Partido] ([idPartido]);

ALTER TABLE [dbo].[EquipoJugador] WITH CHECK
    ADD CONSTRAINT [FK_EquipoJugador_Equipo] FOREIGN KEY([idEquipo]) REFERENCES [dbo].[Equipo] ([idEquipo]);

ALTER TABLE [dbo].[EquipoJugador] WITH CHECK
    ADD CONSTRAINT [FK_EquipoJugador_Jugador] FOREIGN KEY([idJugador]) REFERENCES [dbo].[Jugador] ([idjugador]);
GO

-- ==========================================
-- 5. INSERTAR DATOS DE PRUEBA
-- ==========================================
SET IDENTITY_INSERT [dbo].[Posicion] ON;
INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (1, N'Arquero', N'El que cuida el arco');
INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (2, N'Defensa', N'El que cuida la defensa');
INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (3, N'Mediocampista', N'Dueño del mediocentro');
INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (4, N'Volante', N'Creador de juego');
INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (5, N'Delantero', N'El killer');
SET IDENTITY_INSERT [dbo].[Posicion] OFF;

SET IDENTITY_INSERT [dbo].[Jugador] ON;
INSERT [dbo].[Jugador] ([idjugador], [nombre], [apellido], [fechaRegistro], [idPosicion]) VALUES (17, N'juan nuevo', N'Caceres Perea', CAST(N'2026-09-21' AS Date), 3);
INSERT [dbo].[Jugador] ([idjugador], [nombre], [apellido], [fechaRegistro], [idPosicion]) VALUES (18, N'Sebastian', N'Caceres', CAST(N'2026-09-21' AS Date), 1);
INSERT [dbo].[Jugador] ([idjugador], [nombre], [apellido], [fechaRegistro], [idPosicion]) VALUES (19, N'Sebastian', N'Caceres', CAST(N'2026-09-21' AS Date), 1);
SET IDENTITY_INSERT [dbo].[Jugador] OFF;
GO