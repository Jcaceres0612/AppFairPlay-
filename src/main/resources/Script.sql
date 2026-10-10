USE FairPlay;
GO

-- ==========================================
-- 1. ELIMINAR TABLAS VIEJAS (Para evitar conflictos)
-- ==========================================
DROP TABLE IF EXISTS [dbo].[EquipoJugador];
DROP TABLE IF EXISTS [dbo].[Equipo];
DROP TABLE IF EXISTS [dbo].[Partido];
DROP TABLE IF EXISTS [dbo].[JugadorHabilidad];
DROP TABLE IF EXISTS [dbo].[Habilidad];
DROP TABLE IF EXISTS [dbo].[Jugador];
DROP TABLE IF EXISTS [dbo].[Posicion];
GO

-- ==========================================
-- 2. CREAR LAS TABLAS PRINCIPALES
-- ==========================================
CREATE TABLE [dbo].[Posicion](
                                 [idPosicion] [int] NOT NULL,
                                 [nombrePosicion] [varchar](50) NULL,
                                 [descripcion] [varchar](255) NULL,
                                 PRIMARY KEY CLUSTERED ([idPosicion] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[Jugador](
                                [idjugador] [int] IDENTITY(1,1) NOT NULL,
                                [nombre] [varchar](100) NOT NULL,
                                [apellido] [varchar](100) NOT NULL,
                                [fechaRegistro] [date] NULL,
                                [idPosicion] [int] NULL,
                                PRIMARY KEY CLUSTERED ([idjugador] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[Habilidad](
                                  [idHabilidad] [int] IDENTITY(1,1) NOT NULL,
                                  [nombreHabilidad] [varchar](100) NOT NULL,
                                  PRIMARY KEY CLUSTERED ([idHabilidad] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[Partido](
                                [idPartido] [int] IDENTITY(1,1) NOT NULL,
                                [fechaHora] [datetime] NOT NULL,
                                [tipoPartido] [varchar](50) NOT NULL,
                                [estado] [varchar](50) DEFAULT 'Programado' NULL,
                                PRIMARY KEY CLUSTERED ([idPartido] ASC)
) ON [PRIMARY]
GO

-- ==========================================
-- 3. CREAR TABLAS INTERMEDIAS Y DEPENDIENTES
-- ==========================================
CREATE TABLE [dbo].[JugadorHabilidad](
                                         [idJugador] [int] NOT NULL,
                                         [idHabilidad] [int] NOT NULL,
                                         [valoracion] [int] NOT NULL,
                                         PRIMARY KEY CLUSTERED ([idJugador] ASC, [idHabilidad] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[Equipo](
                               [idEquipo] [int] IDENTITY(1,1) NOT NULL,
                               [nombreEquipo] [varchar](100) NOT NULL,
                               [idPartido] [int] NOT NULL,
                               PRIMARY KEY CLUSTERED ([idEquipo] ASC)
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[EquipoJugador](
                                      [idEquipo] [int] NOT NULL,
                                      [idJugador] [int] NOT NULL,
                                      PRIMARY KEY CLUSTERED ([idEquipo] ASC, [idJugador] ASC)
) ON [PRIMARY]
GO

-- ==========================================
-- 4. AGREGAR TODAS LAS LLAVES FORÁNEAS
-- ==========================================
ALTER TABLE [dbo].[Jugador] WITH CHECK ADD CONSTRAINT [FK_Jugador_Posicion] FOREIGN KEY([idPosicion]) REFERENCES [dbo].[Posicion] ([idPosicion])
GO

ALTER TABLE [dbo].[JugadorHabilidad] WITH CHECK ADD CONSTRAINT [FK_JugadorHabilidad_Jugador] FOREIGN KEY([idJugador]) REFERENCES [dbo].[Jugador] ([idjugador])
GO
ALTER TABLE [dbo].[JugadorHabilidad] WITH CHECK ADD CONSTRAINT [FK_JugadorHabilidad_Habilidad] FOREIGN KEY([idHabilidad]) REFERENCES [dbo].[Habilidad] ([idHabilidad])
GO

ALTER TABLE [dbo].[Equipo] WITH CHECK ADD CONSTRAINT [FK_Equipo_Partido] FOREIGN KEY([idPartido]) REFERENCES [dbo].[Partido] ([idPartido])
GO

ALTER TABLE [dbo].[EquipoJugador] WITH CHECK ADD CONSTRAINT [FK_EquipoJugador_Equipo] FOREIGN KEY([idEquipo]) REFERENCES [dbo].[Equipo] ([idEquipo])
GO
ALTER TABLE [dbo].[EquipoJugador] WITH CHECK ADD CONSTRAINT [FK_EquipoJugador_Jugador] FOREIGN KEY([idJugador]) REFERENCES [dbo].[Jugador] ([idjugador])
GO

-- ==========================================
-- 5. INSERTAR LOS DATOS DE PRUEBA
-- ==========================================
-- Primero las posiciones (obligatorio antes que los jugadores)
INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (1, N'Arquero','El que cuida el arco' )
INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (2, N'Defensa', 'el que cuida la defensa')
INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (3, N'Mediocampista', 'Dueno del mediocentro')
INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (4, N'Volante', 'Creador de juego')
INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (5, N'Delantero', 'el killer')
GO

-- Luego los jugadores
SET IDENTITY_INSERT [dbo].[Jugador] ON
INSERT [dbo].[Jugador] ([idjugador], [nombre], [apellido], [fechaRegistro], [idPosicion]) VALUES (17, N'juan nuevo', N'Caceres Perea', CAST(N'2026-09-21' AS Date), 3)
INSERT [dbo].[Jugador] ([idjugador], [nombre], [apellido], [fechaRegistro], [idPosicion]) VALUES (18, N'Sebastian', N'Caceres', CAST(N'2026-09-21' AS Date), 1)
INSERT [dbo].[Jugador] ([idjugador], [nombre], [apellido], [fechaRegistro], [idPosicion]) VALUES (19, N'Sebastian', N'Caceres', CAST(N'2026-09-21' AS Date), 1)
SET IDENTITY_INSERT [dbo].[Jugador] OFF
GO