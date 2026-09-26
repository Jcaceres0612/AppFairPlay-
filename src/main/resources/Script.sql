USE [FairPlay];
GO
/****** Objeto: Table [dbo].[Jugador] Fecha de script: 23/09/2026 6:08:35 p. m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Jugador](
    [idjugador] [int] IDENTITY(1,1) NOT NULL,
    [nombre] [varchar](100) NOT NULL,
    [apellido] [varchar](100) NOT NULL,
    [fechaRegistro] [date] NULL,
    [idPosicion] [int] NULL,
    PRIMARY KEY CLUSTERED
(
[idjugador] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Objeto: Table [dbo].[Posicion] Fecha de script: 23/09/2026 6:08:35 p. m. ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Posicion](
    [idPosicion] [int] NOT NULL,
    [nombrePosicion] [varchar](50) NULL,
    [descripcion] [varchar](255) NULL,
    PRIMARY KEY CLUSTERED
(
[idPosicion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
    SET IDENTITY_INSERT [dbo].[Jugador] ON

    INSERT [dbo].[Jugador] ([idjugador], [nombre], [apellido], [fechaRegistro], [idPosicion]) VALUES (17, N'juan nuevo ', N'Caceres Perea', CAST(N'2026-09-21' AS Date), 3)
    INSERT [dbo].[Jugador] ([idjugador], [nombre], [apellido], [fechaRegistro], [idPosicion]) VALUES (18, N'Sebastian', N'Caceres', CAST(N'2026-09-21' AS Date), 1)
    INSERT [dbo].[Jugador] ([idjugador], [nombre], [apellido], [fechaRegistro], [idPosicion]) VALUES (19, N'Sebastian', N'Caceres', CAST(N'2026-09-21' AS Date), 1)
    SET IDENTITY_INSERT [dbo].[Jugador] OFF
    GO
    INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (1, N'Arquero', NULL)
    INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (2, N'Defensa', NULL)
    INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (3, N'Mediocampista', NULL)
    INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (4, N'Volante', NULL)
    INSERT [dbo].[Posicion] ([idPosicion], [nombrePosicion], [descripcion]) VALUES (5, N'Delantero', NULL)
    GO
ALTER TABLE [dbo].[Jugador]  WITH CHECK ADD  CONSTRAINT [FK__Jugador__idPosic__5EBF139D] FOREIGN KEY([idPosicion])
    REFERENCES [dbo].[Posicion] ([idPosicion])
    GO
ALTER TABLE [dbo].[Jugador] CHECK CONSTRAINT [FK__Jugador__idPosic__5EBF139D]
    GO