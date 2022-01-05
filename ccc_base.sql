-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 05, 2022 at 01:13 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ccc_base`
--

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `iban` int(16) NOT NULL,
  `name` varchar(50) NOT NULL,
  `debt` int(11) NOT NULL,
  `debt_limit` int(11) NOT NULL,
  `balance` int(11) NOT NULL,
  `exp_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `company_transaction`
--

CREATE TABLE `company_transaction` (
  `id` int(11) NOT NULL,
  `iban_company` int(16) NOT NULL,
  `w_id` int(11) NOT NULL,
  `iban_seller` int(16) NOT NULL,
  `type` enum('PISTWSH','XREWSH','PLHRWMI','EPISTROFI') NOT NULL,
  `cost` int(11) NOT NULL,
  `tr_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `iban` int(16) NOT NULL,
  `name` varchar(50) NOT NULL,
  `balance` int(11) NOT NULL,
  `exp_date` date NOT NULL,
  `debt_limit` int(11) NOT NULL,
  `debt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`iban`, `name`, `balance`, `exp_date`, `debt_limit`, `debt`) VALUES
(7, 'jamesBond', 12, '2021-12-09', 123, 32),
(123, 'qq', 1, '2022-01-13', 0, 2),
(1231, 'dsf', 423, '2022-01-04', 423, 2);

-- --------------------------------------------------------

--
-- Table structure for table `person_transaction`
--

CREATE TABLE `person_transaction` (
  `id` int(11) NOT NULL,
  `iban_person` int(16) DEFAULT NULL,
  `iban_seller` int(16) DEFAULT NULL,
  `type` enum('PISTWSH','XREWSH','PLHRWMI','EPISTROFI') DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  `tr_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `person_transaction`
--

INSERT INTO `person_transaction` (`id`, `iban_person`, `iban_seller`, `type`, `cost`, `tr_date`) VALUES
(3, 12, 21, 'PISTWSH', 12, '2022-01-12');

-- --------------------------------------------------------

--
-- Table structure for table `seller`
--

CREATE TABLE `seller` (
  `iban` int(16) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `debt` int(11) DEFAULT NULL,
  `promithia` int(11) DEFAULT NULL,
  `profit` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `seller`
--

INSERT INTO `seller` (`iban`, `name`, `debt`, `promithia`, `profit`) VALUES
(1234, 'test_seller', 0, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `worker`
--

CREATE TABLE `worker` (
  `iban_company` int(16) NOT NULL,
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`iban`);

--
-- Indexes for table `company_transaction`
--
ALTER TABLE `company_transaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`iban`);

--
-- Indexes for table `person_transaction`
--
ALTER TABLE `person_transaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `seller`
--
ALTER TABLE `seller`
  ADD PRIMARY KEY (`iban`);

--
-- Indexes for table `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`iban_company`,`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `company_transaction`
--
ALTER TABLE `company_transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `person_transaction`
--
ALTER TABLE `person_transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
