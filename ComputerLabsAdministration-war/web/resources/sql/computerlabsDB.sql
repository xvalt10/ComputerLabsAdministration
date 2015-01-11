USE [master]
GO
/****** Object:  Database [ElabAdministration]    Script Date: 12.1.2015 0:42:58 ******/
CREATE DATABASE [ElabAdministration]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'ElabAdministration', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\ElabAdministration.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'ElabAdministration_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\ElabAdministration_log.ldf' , SIZE = 2048KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [ElabAdministration] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [ElabAdministration].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [ElabAdministration] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [ElabAdministration] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [ElabAdministration] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [ElabAdministration] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [ElabAdministration] SET ARITHABORT OFF 
GO
ALTER DATABASE [ElabAdministration] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [ElabAdministration] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [ElabAdministration] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [ElabAdministration] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [ElabAdministration] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [ElabAdministration] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [ElabAdministration] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [ElabAdministration] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [ElabAdministration] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [ElabAdministration] SET  DISABLE_BROKER 
GO
ALTER DATABASE [ElabAdministration] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [ElabAdministration] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [ElabAdministration] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [ElabAdministration] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [ElabAdministration] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [ElabAdministration] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [ElabAdministration] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [ElabAdministration] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [ElabAdministration] SET  MULTI_USER 
GO
ALTER DATABASE [ElabAdministration] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [ElabAdministration] SET DB_CHAINING OFF 
GO
ALTER DATABASE [ElabAdministration] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [ElabAdministration] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [ElabAdministration] SET DELAYED_DURABILITY = DISABLED 
GO
USE [ElabAdministration]
GO
/****** Object:  Table [dbo].[Classrooms]    Script Date: 12.1.2015 0:42:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Classrooms](
	[classRoomId] [int] IDENTITY(1,1) NOT NULL,
	[numberOfSeats] [int] NOT NULL,
	[roomNumber] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Classrooms] PRIMARY KEY CLUSTERED 
(
	[classRoomId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Complaints]    Script Date: 12.1.2015 0:42:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Complaints](
	[complaintId] [int] IDENTITY(1,1) NOT NULL,
	[submissionTimestamp] [datetime] NOT NULL,
	[submittedBy] [int] NOT NULL,
	[assignedTo] [int] NOT NULL,
	[complaintBody] [varchar](max) NOT NULL,
	[complaintTitle] [varchar](50) NOT NULL,
	[solutionTitle] [varchar](50) NULL,
	[solutionBody] [varchar](50) NULL,
	[currentStatus] [varchar](50) NULL,
 CONSTRAINT [PK_Complaints] PRIMARY KEY CLUSTERED 
(
	[complaintId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ComputerLabs]    Script Date: 12.1.2015 0:42:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ComputerLabs](
	[labId] [int] IDENTITY(1,1) NOT NULL,
	[labName] [varchar](50) NOT NULL,
	[instructor] [int] NOT NULL,
	[approvalstatus] [varchar](50) NOT NULL,
 CONSTRAINT [PK_ComputerLabs] PRIMARY KEY CLUSTERED 
(
	[labId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Hardware]    Script Date: 12.1.2015 0:42:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Hardware](
	[hardwareId] [int] IDENTITY(1,1) NOT NULL,
	[dateOfPurchase] [datetime] NOT NULL,
	[expirationOfWarranty] [datetime] NOT NULL,
	[type] [varchar](50) NOT NULL,
	[currentState] [varchar](50) NULL,
	[classRoomId] [int] NOT NULL,
 CONSTRAINT [PK_Hardware] PRIMARY KEY CLUSTERED 
(
	[hardwareId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[InstalledSoftware]    Script Date: 12.1.2015 0:42:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InstalledSoftware](
	[installedSoftwareId] [int] NOT NULL,
	[computerId] [int] NOT NULL,
	[softwareId] [int] NOT NULL,
 CONSTRAINT [PK_InstalledSoftware] PRIMARY KEY CLUSTERED 
(
	[installedSoftwareId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Schedule]    Script Date: 12.1.2015 0:42:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Schedule](
	[scheduleId] [int] IDENTITY(1,1) NOT NULL,
	[labId] [int] NOT NULL,
	[timeslotId] [int] NOT NULL,
 CONSTRAINT [PK_Schedu.e] PRIMARY KEY CLUSTERED 
(
	[scheduleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Software]    Script Date: 12.1.2015 0:42:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Software](
	[softwareId] [int] IDENTITY(1,1) NOT NULL,
	[dateOfPurchae] [datetime] NOT NULL,
	[expirationOfLicence] [datetime] NOT NULL,
	[type] [varchar](50) NOT NULL,
	[currentState] [varchar](50) NULL,
 CONSTRAINT [PK_Software] PRIMARY KEY CLUSTERED 
(
	[softwareId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Timeslot]    Script Date: 12.1.2015 0:42:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Timeslot](
	[timeslotId] [int] IDENTITY(1,1) NOT NULL,
	[day] [int] NOT NULL,
	[startTime] [time](7) NOT NULL,
	[endTime] [time](7) NOT NULL,
	[classRoomId] [int] NOT NULL,
	[isOccupied] [bit] NOT NULL,
 CONSTRAINT [PK_Timeslot] PRIMARY KEY CLUSTERED 
(
	[timeslotId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UserRoles]    Script Date: 12.1.2015 0:42:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UserRoles](
	[roleId] [int] IDENTITY(1,1) NOT NULL,
	[roleName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_UserRoles] PRIMARY KEY CLUSTERED 
(
	[roleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Users]    Script Date: 12.1.2015 0:42:58 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[userId] [int] IDENTITY(1,1) NOT NULL,
	[roleId] [int] NOT NULL,
	[name] [varchar](50) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varchar](64) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[mobileNumber] [varchar](50) NULL,
	[department] [varchar](50) NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Classrooms] ON 

GO
INSERT [dbo].[Classrooms] ([classRoomId], [numberOfSeats], [roomNumber]) VALUES (1, 20, N'100')
GO
SET IDENTITY_INSERT [dbo].[Classrooms] OFF
GO
SET IDENTITY_INSERT [dbo].[Complaints] ON 

GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (1, CAST(N'2015-01-11 10:16:21.097' AS DateTime), 14, 1, N'It''s boiling.', N'Classroom too hot.', N'Turn on the radiator.', N'Turn on the heat.', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (2, CAST(N'2015-01-11 10:19:34.117' AS DateTime), 14, 1, N'It''s boiling.', N'Classroom too hot.', N'Boiling', N'Put on a sweater.', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (3, CAST(N'2015-01-11 11:11:18.357' AS DateTime), 14, 1, N'A new antivirus software should be installed on this computer immediately.', N'Computer 22 contains a deadly virus.', N'Virus deleted', N'Virus deleted', N'Solved')
GO
SET IDENTITY_INSERT [dbo].[Complaints] OFF
GO
SET IDENTITY_INSERT [dbo].[ComputerLabs] ON 

GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor], [approvalstatus]) VALUES (1, N'Applied Informatics', 14, N'Pending')
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor], [approvalstatus]) VALUES (2, N'Advanced Informatics', 13, N'Pending')
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor], [approvalstatus]) VALUES (3, N'Java', 14, N'Pending')
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor], [approvalstatus]) VALUES (4, N'Java II', 14, N'Pending')
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor], [approvalstatus]) VALUES (5, N'Java II', 14, N'Pending')
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor], [approvalstatus]) VALUES (6, N'Java II', 14, N'Pending')
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor], [approvalstatus]) VALUES (7, N'Big data', 14, N'Pending')
GO
SET IDENTITY_INSERT [dbo].[ComputerLabs] OFF
GO
SET IDENTITY_INSERT [dbo].[Schedule] ON 

GO
INSERT [dbo].[Schedule] ([scheduleId], [labId], [timeslotId]) VALUES (1, 1, 5)
GO
INSERT [dbo].[Schedule] ([scheduleId], [labId], [timeslotId]) VALUES (2, 2, 2)
GO
INSERT [dbo].[Schedule] ([scheduleId], [labId], [timeslotId]) VALUES (3, 3, 3)
GO
INSERT [dbo].[Schedule] ([scheduleId], [labId], [timeslotId]) VALUES (4, 4, 1)
GO
INSERT [dbo].[Schedule] ([scheduleId], [labId], [timeslotId]) VALUES (7, 7, 7)
GO
SET IDENTITY_INSERT [dbo].[Schedule] OFF
GO
SET IDENTITY_INSERT [dbo].[Timeslot] ON 

GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (1, 1, CAST(N'07:30:00' AS Time), CAST(N'09:00:00' AS Time), 1, 1)
GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (2, 1, CAST(N'09:15:00' AS Time), CAST(N'10:45:00' AS Time), 1, 1)
GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (3, 1, CAST(N'11:00:00' AS Time), CAST(N'12:30:00' AS Time), 1, 1)
GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (5, 2, CAST(N'07:30:00' AS Time), CAST(N'09:00:00' AS Time), 1, 1)
GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (7, 2, CAST(N'09:15:00' AS Time), CAST(N'10:45:00' AS Time), 1, 1)
GO
SET IDENTITY_INSERT [dbo].[Timeslot] OFF
GO
SET IDENTITY_INSERT [dbo].[UserRoles] ON 

GO
INSERT [dbo].[UserRoles] ([roleId], [roleName]) VALUES (1, N'headOfDepartment')
GO
INSERT [dbo].[UserRoles] ([roleId], [roleName]) VALUES (2, N'instructor')
GO
INSERT [dbo].[UserRoles] ([roleId], [roleName]) VALUES (3, N'technicalStaff')
GO
INSERT [dbo].[UserRoles] ([roleId], [roleName]) VALUES (4, N'admin')
GO
SET IDENTITY_INSERT [dbo].[UserRoles] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 

GO
INSERT [dbo].[Users] ([userId], [roleId], [name], [username], [password], [email], [mobileNumber], [department]) VALUES (1, 4, N'Laco Admin', N'admin', N'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', N'xvalt10@gmail.com', N'0910152882', NULL)
GO
INSERT [dbo].[Users] ([userId], [roleId], [name], [username], [password], [email], [mobileNumber], [department]) VALUES (13, 1, N'Palo Mrkvicka', N'head', N'9f2e6d33a3717ee826353a404ba4618d1aeeb6879ad7936bce8ed5f46814924d', N'xvalt10@gmail.com', N'0910142872', N'Applied Informatics')
GO
INSERT [dbo].[Users] ([userId], [roleId], [name], [username], [password], [email], [mobileNumber], [department]) VALUES (14, 2, N'Feri Profak', N'inst', N'9db333d855b89d618f05694dec53099ec95664efb605d694abdf27b4e450e9ba', N'xvalt10@gmail.com', N'0910142872', N'Applied Informatics')
GO
INSERT [dbo].[Users] ([userId], [roleId], [name], [username], [password], [email], [mobileNumber], [department]) VALUES (15, 3, N'Tomas Aptech', N'tech', N'fe9bbd400bb6cb314531e3462507661401959afc69aae96bc6aec2c213b83bc1', N'xvalt10@gmail.com', N'0912098234', N'IT')
GO
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
ALTER TABLE [dbo].[Complaints]  WITH CHECK ADD  CONSTRAINT [FK_Complaints_Users] FOREIGN KEY([submittedBy])
REFERENCES [dbo].[Users] ([userId])
GO
ALTER TABLE [dbo].[Complaints] CHECK CONSTRAINT [FK_Complaints_Users]
GO
ALTER TABLE [dbo].[Complaints]  WITH CHECK ADD  CONSTRAINT [FK_Complaints_Users1] FOREIGN KEY([assignedTo])
REFERENCES [dbo].[Users] ([userId])
GO
ALTER TABLE [dbo].[Complaints] CHECK CONSTRAINT [FK_Complaints_Users1]
GO
ALTER TABLE [dbo].[ComputerLabs]  WITH CHECK ADD  CONSTRAINT [FK_ComputerLabs_ComputerLabs] FOREIGN KEY([instructor])
REFERENCES [dbo].[Users] ([userId])
GO
ALTER TABLE [dbo].[ComputerLabs] CHECK CONSTRAINT [FK_ComputerLabs_ComputerLabs]
GO
ALTER TABLE [dbo].[Hardware]  WITH CHECK ADD  CONSTRAINT [FK_Hardware_Classrooms] FOREIGN KEY([classRoomId])
REFERENCES [dbo].[Classrooms] ([classRoomId])
GO
ALTER TABLE [dbo].[Hardware] CHECK CONSTRAINT [FK_Hardware_Classrooms]
GO
ALTER TABLE [dbo].[InstalledSoftware]  WITH CHECK ADD  CONSTRAINT [FK_InstalledSoftware_Hardware] FOREIGN KEY([computerId])
REFERENCES [dbo].[Hardware] ([hardwareId])
GO
ALTER TABLE [dbo].[InstalledSoftware] CHECK CONSTRAINT [FK_InstalledSoftware_Hardware]
GO
ALTER TABLE [dbo].[InstalledSoftware]  WITH CHECK ADD  CONSTRAINT [FK_InstalledSoftware_Software] FOREIGN KEY([softwareId])
REFERENCES [dbo].[Software] ([softwareId])
GO
ALTER TABLE [dbo].[InstalledSoftware] CHECK CONSTRAINT [FK_InstalledSoftware_Software]
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD  CONSTRAINT [FK_Schedule_ComputerLabs] FOREIGN KEY([labId])
REFERENCES [dbo].[ComputerLabs] ([labId])
GO
ALTER TABLE [dbo].[Schedule] CHECK CONSTRAINT [FK_Schedule_ComputerLabs]
GO
ALTER TABLE [dbo].[Schedule]  WITH CHECK ADD  CONSTRAINT [FK_Schedule_Timeslot] FOREIGN KEY([timeslotId])
REFERENCES [dbo].[Timeslot] ([timeslotId])
GO
ALTER TABLE [dbo].[Schedule] CHECK CONSTRAINT [FK_Schedule_Timeslot]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [FK_Users_Users] FOREIGN KEY([roleId])
REFERENCES [dbo].[UserRoles] ([roleId])
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [FK_Users_Users]
GO
USE [master]
GO
ALTER DATABASE [ElabAdministration] SET  READ_WRITE 
GO
