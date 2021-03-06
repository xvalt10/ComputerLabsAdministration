USE [master]
GO
/****** Object:  Database [ElabAdministration]    Script Date: 21.1.2015 21:40:07 ******/
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
/****** Object:  Table [dbo].[Classrooms]    Script Date: 21.1.2015 21:40:07 ******/
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
/****** Object:  Table [dbo].[Complaints]    Script Date: 21.1.2015 21:40:07 ******/
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
/****** Object:  Table [dbo].[ComputerLabs]    Script Date: 21.1.2015 21:40:07 ******/
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
 CONSTRAINT [PK_ComputerLabs] PRIMARY KEY CLUSTERED 
(
	[labId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Hardware]    Script Date: 21.1.2015 21:40:07 ******/
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
	[seatNo] [int] NULL,
 CONSTRAINT [PK_Hardware] PRIMARY KEY CLUSTERED 
(
	[hardwareId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[InstalledSoftware]    Script Date: 21.1.2015 21:40:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[InstalledSoftware](
	[installedSoftwareId] [int] IDENTITY(1,1) NOT NULL,
	[computerId] [int] NOT NULL,
	[softwareId] [int] NOT NULL,
 CONSTRAINT [PK_InstalledSoftware] PRIMARY KEY CLUSTERED 
(
	[installedSoftwareId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Notification]    Script Date: 21.1.2015 21:40:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Notification](
	[notificationId] [int] IDENTITY(1,1) NOT NULL,
	[notificationTitle] [varchar](50) NOT NULL,
	[notificationText] [varchar](max) NOT NULL,
	[userId] [int] NOT NULL,
	[notificationWasRead] [bit] NOT NULL,
	[creationTimestamp] [datetime] NOT NULL,
 CONSTRAINT [PK_Notification] PRIMARY KEY CLUSTERED 
(
	[notificationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Schedule]    Script Date: 21.1.2015 21:40:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Schedule](
	[scheduleId] [int] IDENTITY(1,1) NOT NULL,
	[labId] [int] NOT NULL,
	[timeslotId] [int] NOT NULL,
	[approvalStatus] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Schedu.e] PRIMARY KEY CLUSTERED 
(
	[scheduleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[sitePost]    Script Date: 21.1.2015 21:40:07 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[sitePost](
	[sitePostId] [int] IDENTITY(1,1) NOT NULL,
	[postTitle] [nvarchar](50) NOT NULL,
	[postText] [nvarchar](max) NOT NULL,
	[attachmentFileName] [nvarchar](max) NULL,
	[labId] [int] NOT NULL,
	[submittedBy] [int] NOT NULL,
	[submissionDate] [datetime] NOT NULL,
 CONSTRAINT [PK_sitePost] PRIMARY KEY CLUSTERED 
(
	[sitePostId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Software]    Script Date: 21.1.2015 21:40:07 ******/
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
/****** Object:  Table [dbo].[Timeslot]    Script Date: 21.1.2015 21:40:07 ******/
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
/****** Object:  Table [dbo].[UserRoles]    Script Date: 21.1.2015 21:40:07 ******/
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
/****** Object:  Table [dbo].[Users]    Script Date: 21.1.2015 21:40:07 ******/
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
INSERT [dbo].[Classrooms] ([classRoomId], [numberOfSeats], [roomNumber]) VALUES (1, 70, N'200')
GO
INSERT [dbo].[Classrooms] ([classRoomId], [numberOfSeats], [roomNumber]) VALUES (10, 25, N'160')
GO
INSERT [dbo].[Classrooms] ([classRoomId], [numberOfSeats], [roomNumber]) VALUES (19, 30, N'30')
GO
INSERT [dbo].[Classrooms] ([classRoomId], [numberOfSeats], [roomNumber]) VALUES (30, 54, N'54')
GO
SET IDENTITY_INSERT [dbo].[Classrooms] OFF
GO
SET IDENTITY_INSERT [dbo].[Complaints] ON 

GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (1, CAST(N'2015-01-11 10:16:21.097' AS DateTime), 14, 1, N'It''s boiling.', N'Classroom too hot.', N'Turn on the radiator.', N'Turn on the heat.', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (2, CAST(N'2015-01-11 10:19:34.117' AS DateTime), 14, 1, N'It''s boiling.', N'Classroom too hot.', N'Boiling', N'Put on a sweater.', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (3, CAST(N'2015-01-11 11:11:18.357' AS DateTime), 14, 15, N'A new antivirus software should be installed on this computer immediately.', N'Computer 22 contains a deadly virus.', N'Virus deleted', N'Virus deleted', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (4, CAST(N'2015-01-19 18:18:30.140' AS DateTime), 13, 15, N'Netbeans has still haven''t been installed. Please install it ASAP.', N'Netbeans not installed on computers in room 300.', N'jlkjkl', N'jljkjklj', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (5, CAST(N'2015-01-19 18:33:00.833' AS DateTime), 14, 1, N'Netbeans has still haven''t been installed. Please install it ASAP.', N'Netbeans not installed on computers in room 300.', N'jlkjkl', N'jljkjklj', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (6, CAST(N'2015-01-19 18:40:12.947' AS DateTime), 14, 1, N'Netbeans has still haven''t been installed. Please install it ASAP.', N'Netbeans not installed on computers in room 300.', N'jlkjkl', N'jljkjklj', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (7, CAST(N'2015-01-19 19:44:10.343' AS DateTime), 13, 15, N'l;ll;l', N'klkl', N'fsdfd', N'fdsfs', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (8, CAST(N'2015-01-19 22:12:12.990' AS DateTime), 13, 15, N'jljljljk', N'jjljlkj', N'fafa', N'fdafda', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (9, CAST(N'2015-01-19 22:23:15.483' AS DateTime), 14, 15, N'fdafd', N'fda', N'fdafda', N'fdafasf', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (10, CAST(N'2015-01-21 19:02:02.523' AS DateTime), 14, 15, N'Printer in classroom 200 is not working.', N'Printer not working', N'New printer installed.', N'Old printer has been replaced with a new one.', N'Solved')
GO
INSERT [dbo].[Complaints] ([complaintId], [submissionTimestamp], [submittedBy], [assignedTo], [complaintBody], [complaintTitle], [solutionTitle], [solutionBody], [currentStatus]) VALUES (11, CAST(N'2015-01-21 19:13:55.007' AS DateTime), 14, 15, N'fdsafsdaf', N'fsfsd', N'fsdaf', N'fafsa', N'Solved')
GO
SET IDENTITY_INSERT [dbo].[Complaints] OFF
GO
SET IDENTITY_INSERT [dbo].[ComputerLabs] ON 

GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (1, N'Applied Informatics', 14)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (2, N'Advanced Informatics', 13)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (3, N'Java', 14)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (6, N'Java II', 14)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (7, N'Big data', 14)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (8, N'C# ', 13)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (11, N'Advanced javascript', 13)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (13, N'DB basics', 14)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (16, N'PHP basics', 13)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (17, N'fdafaf', 14)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (18, N'fsfsds', 14)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (19, N'dfsfsss', 14)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (20, N'klk;lk;lk;l', 14)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (21, N'kljkjkljlkjlkj', 14)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (22, N'jkljljlkj', 14)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (23, N'MS Project', 13)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (24, N'SQL basics', 13)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (25, N'fdfafdffs', 13)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (26, N'Oracle DB', 13)
GO
INSERT [dbo].[ComputerLabs] ([labId], [labName], [instructor]) VALUES (27, N'xzczcz', 13)
GO
SET IDENTITY_INSERT [dbo].[ComputerLabs] OFF
GO
SET IDENTITY_INSERT [dbo].[Hardware] ON 

GO
INSERT [dbo].[Hardware] ([hardwareId], [dateOfPurchase], [expirationOfWarranty], [type], [currentState], [classRoomId], [seatNo]) VALUES (1, CAST(N'2015-01-01 01:00:00.000' AS DateTime), CAST(N'2020-01-01 01:00:00.000' AS DateTime), N'Asus Ultrabook A2087', N'new', 1, 1)
GO
INSERT [dbo].[Hardware] ([hardwareId], [dateOfPurchase], [expirationOfWarranty], [type], [currentState], [classRoomId], [seatNo]) VALUES (5, CAST(N'2015-01-15 01:00:00.000' AS DateTime), CAST(N'2015-01-14 01:00:00.000' AS DateTime), N'Acer TravelMate 1000', N'new', 1, 2)
GO
INSERT [dbo].[Hardware] ([hardwareId], [dateOfPurchase], [expirationOfWarranty], [type], [currentState], [classRoomId], [seatNo]) VALUES (8, CAST(N'2015-01-01 01:00:00.000' AS DateTime), CAST(N'2017-01-01 01:00:00.000' AS DateTime), N'Acer TravelMate 900', N'damaged', 10, 1)
GO
SET IDENTITY_INSERT [dbo].[Hardware] OFF
GO
SET IDENTITY_INSERT [dbo].[InstalledSoftware] ON 

GO
INSERT [dbo].[InstalledSoftware] ([installedSoftwareId], [computerId], [softwareId]) VALUES (1, 1, 1)
GO
INSERT [dbo].[InstalledSoftware] ([installedSoftwareId], [computerId], [softwareId]) VALUES (2, 5, 3)
GO
INSERT [dbo].[InstalledSoftware] ([installedSoftwareId], [computerId], [softwareId]) VALUES (5, 1, 5)
GO
INSERT [dbo].[InstalledSoftware] ([installedSoftwareId], [computerId], [softwareId]) VALUES (6, 1, 3)
GO
INSERT [dbo].[InstalledSoftware] ([installedSoftwareId], [computerId], [softwareId]) VALUES (7, 8, 7)
GO
SET IDENTITY_INSERT [dbo].[InstalledSoftware] OFF
GO
SET IDENTITY_INSERT [dbo].[Notification] ON 

GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (1, N'Lab request', N'Requested lab:fdfafdffs Timeslot:11:00-12:30 Classroom:200', 1, 0, CAST(N'2015-01-21 18:17:52.250' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (2, N'Submitted complaint:Printer not working', N'Printer in classroom 200 is not working.', 1, 0, CAST(N'2015-01-21 19:02:02.587' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (3, N'Ticket assigned:10', N'Printer in classroom 200 is not working.', 15, 0, CAST(N'2015-01-21 19:04:59.410' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (4, N'Complaint solved:Printer not working', N'New printer installed.', 14, 0, CAST(N'2015-01-21 19:11:00.347' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (5, N'Submitted complaint:fsfsd', N'fdsafsdaf', 1, 0, CAST(N'2015-01-21 19:13:55.050' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (6, N'Ticket assigned:11', N'fdsafsdaf', 15, 0, CAST(N'2015-01-21 19:14:19.223' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (7, N'Complaint solved:fsfsd', N'fsdaf', 14, 0, CAST(N'2015-01-21 19:14:48.217' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (8, N'Lab request', N'Requested lab:Oracle DB Timeslot:11:00-12:30 Classroom:200', 1, 0, CAST(N'2015-01-21 19:17:27.210' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (9, N'Lab request', N'Requested lab:xzczcz Timeslot:11:00-12:30 Classroom:200', 1, 0, CAST(N'2015-01-21 19:34:57.917' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (10, N'Lab request rejected', N'Requested lab:xzczcz Timeslot:07:30-09:00 Classroom:30', 13, 0, CAST(N'2015-01-21 19:35:47.450' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (11, N'Lab request rejected', N'Requested lab:xzczcz Timeslot:07:30-09:00 Classroom:30', 14, 0, CAST(N'2015-01-21 19:35:47.457' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (12, N'HW warranty expiration alert', N'Hardware name:Acer TravelMate 1000
Hardware location:classroom no.200,seat number:2
Warranty expiration date:Wed Jan 14 01:00:00 CET 2015

', 1, 0, CAST(N'2015-01-21 21:06:10.150' AS DateTime))
GO
INSERT [dbo].[Notification] ([notificationId], [notificationTitle], [notificationText], [userId], [notificationWasRead], [creationTimestamp]) VALUES (13, N'SW licence expiration alert', N'Software name:Windows 7
Licence expiration date:Wed Jan 21 01:00:00 CET 2015

', 1, 0, CAST(N'2015-01-21 21:33:14.010' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[Notification] OFF
GO
SET IDENTITY_INSERT [dbo].[Schedule] ON 

GO
INSERT [dbo].[Schedule] ([scheduleId], [labId], [timeslotId], [approvalStatus]) VALUES (1, 1, 5, N'Approved')
GO
INSERT [dbo].[Schedule] ([scheduleId], [labId], [timeslotId], [approvalStatus]) VALUES (2, 2, 2, N'Approved')
GO
INSERT [dbo].[Schedule] ([scheduleId], [labId], [timeslotId], [approvalStatus]) VALUES (8, 8, 1, N'Approved')
GO
INSERT [dbo].[Schedule] ([scheduleId], [labId], [timeslotId], [approvalStatus]) VALUES (11, 11, 7, N'Approved')
GO
INSERT [dbo].[Schedule] ([scheduleId], [labId], [timeslotId], [approvalStatus]) VALUES (22, 22, 10, N'Approved')
GO
INSERT [dbo].[Schedule] ([scheduleId], [labId], [timeslotId], [approvalStatus]) VALUES (23, 23, 11, N'Approved')
GO
SET IDENTITY_INSERT [dbo].[Schedule] OFF
GO
SET IDENTITY_INSERT [dbo].[sitePost] ON 

GO
INSERT [dbo].[sitePost] ([sitePostId], [postTitle], [postText], [attachmentFileName], [labId], [submittedBy], [submissionDate]) VALUES (10, N'Course syllabus', N'In the attachment you will find the course sylabus for Advanced Java.', N'Assign2.docx', 6, 1, CAST(N'2015-01-18 08:06:13.540' AS DateTime))
GO
INSERT [dbo].[sitePost] ([sitePostId], [postTitle], [postText], [attachmentFileName], [labId], [submittedBy], [submissionDate]) VALUES (11, N'Assignment1', N'In the attachment you will find assignment 1.', N'Assign2.docx', 13, 13, CAST(N'2015-01-18 11:25:20.230' AS DateTime))
GO
INSERT [dbo].[sitePost] ([sitePostId], [postTitle], [postText], [attachmentFileName], [labId], [submittedBy], [submissionDate]) VALUES (12, N'Start of course postponed', N'Due to illness of the course teacher, the course will start on the 27th September', NULL, 13, 13, CAST(N'2015-01-19 18:05:10.427' AS DateTime))
GO
INSERT [dbo].[sitePost] ([sitePostId], [postTitle], [postText], [attachmentFileName], [labId], [submittedBy], [submissionDate]) VALUES (13, N'Final exam', N'The final exam will take place on the 14th December 2015.', NULL, 6, 13, CAST(N'2015-01-19 18:09:48.193' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[sitePost] OFF
GO
SET IDENTITY_INSERT [dbo].[Software] ON 

GO
INSERT [dbo].[Software] ([softwareId], [dateOfPurchae], [expirationOfLicence], [type], [currentState]) VALUES (1, CAST(N'2015-01-01 01:00:00.000' AS DateTime), CAST(N'2016-01-01 01:00:00.000' AS DateTime), N'MS Office 2013', N'functional')
GO
INSERT [dbo].[Software] ([softwareId], [dateOfPurchae], [expirationOfLicence], [type], [currentState]) VALUES (3, CAST(N'2015-01-22 01:00:00.000' AS DateTime), CAST(N'2015-01-21 01:00:00.000' AS DateTime), N'Windows 7', N'ok')
GO
INSERT [dbo].[Software] ([softwareId], [dateOfPurchae], [expirationOfLicence], [type], [currentState]) VALUES (5, CAST(N'2015-01-31 01:00:00.000' AS DateTime), CAST(N'2016-01-31 01:00:00.000' AS DateTime), N'Adobe Photoshop CS', N'new')
GO
INSERT [dbo].[Software] ([softwareId], [dateOfPurchae], [expirationOfLicence], [type], [currentState]) VALUES (7, CAST(N'2015-01-14 01:00:00.000' AS DateTime), CAST(N'2015-02-12 01:00:00.000' AS DateTime), N'MS Visio', N'ok')
GO
SET IDENTITY_INSERT [dbo].[Software] OFF
GO
SET IDENTITY_INSERT [dbo].[Timeslot] ON 

GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (1, 1, CAST(N'07:30:00' AS Time), CAST(N'09:00:00' AS Time), 1, 1)
GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (2, 1, CAST(N'09:15:00' AS Time), CAST(N'10:45:00' AS Time), 1, 1)
GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (3, 1, CAST(N'11:00:00' AS Time), CAST(N'12:30:00' AS Time), 1, 0)
GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (5, 2, CAST(N'07:30:00' AS Time), CAST(N'09:00:00' AS Time), 1, 1)
GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (7, 2, CAST(N'09:15:00' AS Time), CAST(N'10:45:00' AS Time), 1, 1)
GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (10, 5, CAST(N'07:30:00' AS Time), CAST(N'09:00:00' AS Time), 10, 1)
GO
INSERT [dbo].[Timeslot] ([timeslotId], [day], [startTime], [endTime], [classRoomId], [isOccupied]) VALUES (11, 5, CAST(N'07:30:00' AS Time), CAST(N'09:00:00' AS Time), 19, 1)
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
INSERT [dbo].[Users] ([userId], [roleId], [name], [username], [password], [email], [mobileNumber], [department]) VALUES (1, 4, N'Laco Admin', N'admin', N'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', N'xvalt10@gmail.com', N'0910152882', N'IT department')
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
ALTER TABLE [dbo].[Notification]  WITH CHECK ADD  CONSTRAINT [FK_Notification_Users] FOREIGN KEY([userId])
REFERENCES [dbo].[Users] ([userId])
GO
ALTER TABLE [dbo].[Notification] CHECK CONSTRAINT [FK_Notification_Users]
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
ALTER TABLE [dbo].[sitePost]  WITH CHECK ADD  CONSTRAINT [FK_sitePost_ComputerLabs] FOREIGN KEY([labId])
REFERENCES [dbo].[ComputerLabs] ([labId])
GO
ALTER TABLE [dbo].[sitePost] CHECK CONSTRAINT [FK_sitePost_ComputerLabs]
GO
ALTER TABLE [dbo].[sitePost]  WITH CHECK ADD  CONSTRAINT [FK_sitePost_Users] FOREIGN KEY([submittedBy])
REFERENCES [dbo].[Users] ([userId])
GO
ALTER TABLE [dbo].[sitePost] CHECK CONSTRAINT [FK_sitePost_Users]
GO
ALTER TABLE [dbo].[Timeslot]  WITH CHECK ADD  CONSTRAINT [FK_Timeslot_Classrooms] FOREIGN KEY([classRoomId])
REFERENCES [dbo].[Classrooms] ([classRoomId])
GO
ALTER TABLE [dbo].[Timeslot] CHECK CONSTRAINT [FK_Timeslot_Classrooms]
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
