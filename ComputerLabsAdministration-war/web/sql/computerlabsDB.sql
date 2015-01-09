USE [master]
GO
/****** Object:  Database [ElabAdministration]    Script Date: 9.1.2015 23:35:03 ******/
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
/****** Object:  Table [dbo].[Complaints]    Script Date: 9.1.2015 23:35:03 ******/
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
/****** Object:  Table [dbo].[ComputerLabs]    Script Date: 9.1.2015 23:35:03 ******/
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
/****** Object:  Table [dbo].[Hardware]    Script Date: 9.1.2015 23:35:03 ******/
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
 CONSTRAINT [PK_Hardware] PRIMARY KEY CLUSTERED 
(
	[hardwareId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[InstalledSoftware]    Script Date: 9.1.2015 23:35:03 ******/
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
/****** Object:  Table [dbo].[Schedule]    Script Date: 9.1.2015 23:35:03 ******/
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
/****** Object:  Table [dbo].[Software]    Script Date: 9.1.2015 23:35:03 ******/
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
/****** Object:  Table [dbo].[Timeslot]    Script Date: 9.1.2015 23:35:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Timeslot](
	[timeslotId] [int] NOT NULL,
	[day] [int] IDENTITY(1,1) NOT NULL,
	[startTime] [time](7) NOT NULL,
	[endTime] [time](7) NOT NULL,
 CONSTRAINT [PK_Timeslot] PRIMARY KEY CLUSTERED 
(
	[timeslotId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[UserRoles]    Script Date: 9.1.2015 23:35:03 ******/
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
/****** Object:  Table [dbo].[Users]    Script Date: 9.1.2015 23:35:03 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[userId] [int] IDENTITY(1,1) NOT NULL,
	[roleId] [int] NOT NULL,
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
