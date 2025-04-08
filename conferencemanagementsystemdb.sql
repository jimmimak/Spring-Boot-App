-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 22, 2024 at 09:59 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `conferencemanagementsystemdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `authenticationtoken`
--

CREATE TABLE `authenticationtoken` (
  `Tokenid` int(11) NOT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Tokenvalue` varchar(255) NOT NULL,
  `Expirydate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `conference`
--

CREATE TABLE `conference` (
  `Name` varchar(255) NOT NULL,
  `Description` text NOT NULL,
  `Creationdate` date NOT NULL,
  `State` varchar(50) DEFAULT NULL CHECK (`State` in ('CREATED','SUBMISSION','ASSIGNMENT','REVIEW','DECISION','FINAL_SUBMISSION','FINAL'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `conference`
--

INSERT INTO `conference` (`Name`, `Description`, `Creationdate`, `State`) VALUES
('EcoFuture 2024', 'Exploring sustainable technologies for the future.', '2023-12-22', 'DECISION'),
('Global AI Summit', 'An international conference on artificial intelligence.', '2023-11-30', 'FINAL'),
('InnovateX', 'Innovation and technology in the modern world.', '2023-11-10', 'DECISION'),
('TechCon 2024', 'A conference focusing on emerging technologies.', '2023-12-20', 'FINAL');

-- --------------------------------------------------------

--
-- Table structure for table `conferenceuser`
--

CREATE TABLE `conferenceuser` (
  `Name` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL,
  `Roleid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `conferenceuser`
--

INSERT INTO `conferenceuser` (`Name`, `Username`, `Roleid`) VALUES
('EcoFuture 2024', 'user8', 3),
('Global AI Summit', 'user6', 3),
('Global AI Summit', 'user7', 3),
('InnovateX', 'user5', 3),
('TechCon 2024', 'user1', 3),
('TechCon 2024', 'user2', 3),
('TechCon 2024', 'user3', 3),
('TechCon 2024', 'user4', 3);

-- --------------------------------------------------------

--
-- Table structure for table `paper`
--

CREATE TABLE `paper` (
  `Title` varchar(255) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Abstract` text NOT NULL,
  `Content` text DEFAULT NULL,
  `Creationdate` date NOT NULL,
  `State` varchar(50) DEFAULT NULL CHECK (`State` in ('CREATED','SUBMITTED','REVIEWED','REJECTED','APPROVED','ACCEPTED'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `paper`
--

INSERT INTO `paper` (`Title`, `Name`, `Abstract`, `Content`, `Creationdate`, `State`) VALUES
('5G and IoT', 'TechCon 2024', 'Impact of 5G on IoT devices and networks.', 'Content of the paper.', '2024-01-10', 'ACCEPTED'),
('AI and Global Trends', 'Global AI Summit', 'Impact of AI on global industries.', 'Content of the paper.', '2023-12-05', 'REJECTED'),
('AI for Healthcare', 'Global AI Summit', 'Artificial intelligence in modern healthcare systems.', 'Content of the paper.', '2023-12-10', 'ACCEPTED'),
('Blockchain Innovations', 'TechCon 2024', 'The latest developments in blockchain technology.', 'Content of the paper.', '2024-01-08', 'ACCEPTED'),
('Emerging Tech in 2024', 'TechCon 2024', 'Exploring the latest in tech for 2024.', 'Content of the paper.', '2023-12-23', 'REJECTED'),
('Innovating the Future', 'InnovateX', 'Innovation trends in technology.', 'Content of the paper.', '2023-11-20', 'APPROVED'),
('Next-Gen Virtual Reality', 'TechCon 2024', 'Exploring the future of virtual reality.', 'Content of the paper.', '2024-01-08', 'ACCEPTED'),
('Sustainable Tech', 'EcoFuture 2024', 'Sustainable technologies for the future.', 'Content of the paper.', '2024-01-10', 'APPROVED');

-- --------------------------------------------------------

--
-- Table structure for table `paperauthor`
--

CREATE TABLE `paperauthor` (
  `Title` varchar(255) NOT NULL,
  `Username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `paperauthor`
--

INSERT INTO `paperauthor` (`Title`, `Username`) VALUES
('5G and IoT', 'user4'),
('AI and Global Trends', 'user6'),
('AI for Healthcare', 'user7'),
('Blockchain Innovations', 'user3'),
('Emerging Tech in 2024', 'user1'),
('Innovating the Future', 'user5'),
('Next-Gen Virtual Reality', 'user2'),
('Sustainable Tech', 'user8');

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `Reviewid` int(11) NOT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Score` int(11) DEFAULT NULL CHECK (`Score` >= 0 and `Score` <= 100),
  `Comments` text DEFAULT NULL,
  `Reviewdate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `Roleid` int(11) NOT NULL,
  `Rolename` varchar(50) NOT NULL CHECK (`Rolename` in ('USER','ADMIN','AUTHOR','PC_CHAIR','PC_MEMBER'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`Roleid`, `Rolename`) VALUES
(2, 'ADMIN'),
(3, 'AUTHOR'),
(4, 'PC_CHAIR'),
(5, 'PC_MEMBER'),
(1, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `userrole`
--

CREATE TABLE `userrole` (
  `Username` varchar(255) NOT NULL,
  `Roleid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `userrole`
--

INSERT INTO `userrole` (`Username`, `Roleid`) VALUES
('user1', 1),
('user1', 3),
('user2', 1),
('user2', 3),
('user3', 1),
('user3', 3),
('user4', 1),
('user4', 3),
('user5', 1),
('user5', 3),
('user6', 1),
('user6', 3),
('user7', 1),
('user7', 3),
('user8', 1),
('user8', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `authenticationtoken`
--
ALTER TABLE `authenticationtoken`
  ADD PRIMARY KEY (`Tokenid`),
  ADD UNIQUE KEY `Tokenvalue` (`Tokenvalue`),
  ADD KEY `Username` (`Username`);

--
-- Indexes for table `conference`
--
ALTER TABLE `conference`
  ADD PRIMARY KEY (`Name`);

--
-- Indexes for table `conferenceuser`
--
ALTER TABLE `conferenceuser`
  ADD PRIMARY KEY (`Name`,`Username`,`Roleid`),
  ADD KEY `Username` (`Username`),
  ADD KEY `Roleid` (`Roleid`);

--
-- Indexes for table `paper`
--
ALTER TABLE `paper`
  ADD PRIMARY KEY (`Title`),
  ADD KEY `Name` (`Name`);

--
-- Indexes for table `paperauthor`
--
ALTER TABLE `paperauthor`
  ADD PRIMARY KEY (`Title`,`Username`),
  ADD KEY `Username` (`Username`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`Reviewid`),
  ADD KEY `Title` (`Title`),
  ADD KEY `Username` (`Username`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`Roleid`),
  ADD UNIQUE KEY `Rolename` (`Rolename`);

--
-- Indexes for table `userrole`
--
ALTER TABLE `userrole`
  ADD PRIMARY KEY (`Username`,`Roleid`),
  ADD KEY `Roleid` (`Roleid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `authenticationtoken`
--
ALTER TABLE `authenticationtoken`
  MODIFY `Tokenid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `Reviewid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `Roleid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `authenticationtoken`
--
ALTER TABLE `authenticationtoken`
  ADD CONSTRAINT `authenticationtoken_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `appuser` (`Username`);

--
-- Constraints for table `conferenceuser`
--
ALTER TABLE `conferenceuser`
  ADD CONSTRAINT `conferenceuser_ibfk_1` FOREIGN KEY (`Name`) REFERENCES `conference` (`Name`),
  ADD CONSTRAINT `conferenceuser_ibfk_2` FOREIGN KEY (`Username`) REFERENCES `appuser` (`Username`),
  ADD CONSTRAINT `conferenceuser_ibfk_3` FOREIGN KEY (`Roleid`) REFERENCES `role` (`Roleid`);

--
-- Constraints for table `paper`
--
ALTER TABLE `paper`
  ADD CONSTRAINT `paper_ibfk_1` FOREIGN KEY (`Name`) REFERENCES `conference` (`Name`);

--
-- Constraints for table `paperauthor`
--
ALTER TABLE `paperauthor`
  ADD CONSTRAINT `paperauthor_ibfk_1` FOREIGN KEY (`Title`) REFERENCES `paper` (`Title`),
  ADD CONSTRAINT `paperauthor_ibfk_2` FOREIGN KEY (`Username`) REFERENCES `appuser` (`Username`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `review_ibfk_1` FOREIGN KEY (`Title`) REFERENCES `paper` (`Title`),
  ADD CONSTRAINT `review_ibfk_2` FOREIGN KEY (`Username`) REFERENCES `appuser` (`Username`);

--
-- Constraints for table `userrole`
--
ALTER TABLE `userrole`
  ADD CONSTRAINT `userrole_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `appuser` (`Username`),
  ADD CONSTRAINT `userrole_ibfk_2` FOREIGN KEY (`Roleid`) REFERENCES `role` (`Roleid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
