-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 19, 2017 at 04:14 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `posdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `inventory`
--

CREATE TABLE `inventory` (
  `inv_id` int(3) NOT NULL,
  `prod_id` int(3) NOT NULL,
  `quantity` int(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `prod_id` int(3) NOT NULL,
  `prod_name` varchar(60) NOT NULL,
  `prod_price` int(10) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`prod_id`, `prod_name`, `prod_price`, `quantity`) VALUES
(1, 'corned beef', 33, 59),
(2, 'corned tuna', 36, 20),
(3, 'chicken noodles', 7, 12),
(4, 'beef noodles', 7, 11),
(5, 'pancit canton', 6, 15),
(7, 'pork and beans', 22, 62),
(8, 'beef loaf', 17, 65),
(9, 'tuna afritada', 16, 29),
(11, 'spam', 120, 19),
(12, 'ham', 57, 38),
(13, 'bacon', 153, 21),
(14, 'piattos', 12, 20),
(15, 'nova', 12, 55),
(17, 'moby', 6, 53),
(18, 'berry knots', 5, 87),
(19, 'choco knots', 5, 55),
(21, 'cheese ring', 5, 52),
(22, 'sky flakes', 54, 44),
(23, 'ace crackers', 60, 22),
(24, 'rebisco strawberry', 60, 55),
(26, 'cream-o', 60, 20),
(30, 'koko crunch', 15, 45),
(31, 'mikmik', 2, 12),
(32, 'test', 26, 109),
(33, 'test_prod', 50, 500);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(3) UNSIGNED NOT NULL,
  `username` varchar(20) NOT NULL,
  `user_type` int(2) NOT NULL,
  `user_fullname` text NOT NULL,
  `password` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `user_type`, `user_fullname`, `password`) VALUES
(2, 'testUser', 2, 'Test User', '87C80BA48A1A68BB5249FFDBAE5615B19A4AAC6A10B2DB6BEBD7B98723BA07FF'),
(6, 'admin1', 1, 'Administrator 1', '42F2121A8A4B9C1AA978E2E481987EE4944C6E1E605C811A280D0A87D28B3BF2'),
(7, 'cashier', 2, 'Cashier', '80A4EAB31B3A6EBEE696AE647190D060787643FED5CBC1B835BE78A0BF7924C5'),
(8, 'dar', 2, 'daryl', '252F0DF53C37EF71640C7D11A58812B4307939C6BAF4009E824B82752CA1565B'),
(9, 'a', 1, 'admin', '638FDC1D784C253FBA594FBE47A53AC6F183F2DEF86DBFA06D969305542927F7'),
(10, 'c', 2, 'cashier', '81F43BA8202B97B9C62D33CF04E456AC9EAB503D8246B59F8240A02B019B852B'),
(11, 'dm', 1, 'dm', '252F0DF53C37EF71640C7D11A58812B4307939C6BAF4009E824B82752CA1565B'),
(12, 'ca', 2, 'cashier', '8213358B1AC8F75E9184D431FA224B44857E2E37C8555B7228C6B70FAFE7E3C2'),
(13, 'new', 2, 'new', '268EAA350988AC76A5850F4B81D699AF31B5F41DE5517596AEE5702EECE091D3'),
(16, 'caa', 2, 'sdf', '8213358B1AC8F75E9184D431FA224B44857E2E37C8555B7228C6B70FAFE7E3C2'),
(17, 'daryl', 1, 'dar', '960D830E9237D4BDE83E806541F4FD61889F8FD0B3330DC4D38542AA7205EFF9'),
(18, 'newUser', 0, 'new user', '268EAA350988AC76A5850F4B81D699AF31B5F41DE5517596AEE5702EECE091D3'),
(19, 'darylmae', 0, 'daryl mae', '49ADF600ADF4452FEB6CE427E8A9344A23B49179095E1299604E206A1178C19F'),
(20, 't1', 0, 't1', '837FF5D4ED181DE9503EE564628D427B931B94EFDBEF9C457C627EF0E13E1027'),
(21, 'bbbb', 0, 'bb', '3A90CC61736C2E178D8DC7AF24C6E0F7BB98E14830A17D171C062E75147EE039'),
(22, 'khent', 1, 'khentdall', '6C5CF431FC94B8FF3EB941D15AE23F42B6331FB9E69308B6CC8E11CA5DAC3919'),
(23, 'newTest', 2, 'Testing', '268EAA350988AC76A5850F4B81D699AF31B5F41DE5517596AEE5702EECE091D3'),
(24, 'new_user', 2, 'new_user', '268EAA350988AC76A5850F4B81D699AF31B5F41DE5517596AEE5702EECE091D3'),
(25, 'web', 0, 'web user', 'A6ACADF18544E3A18ADF79CF21DA02A1A50AE71F1518846A827BE0FFBA6AB3CB'),
(26, 'webbb', 0, 'web_user', 'A6ACADF18544E3A18ADF79CF21DA02A1A50AE71F1518846A827BE0FFBA6AB3CB'),
(27, 'web_cashier', 0, 'ccc', 'A6ACADF18544E3A18ADF79CF21DA02A1A50AE71F1518846A827BE0FFBA6AB3CB'),
(28, 'abc', 1, 'abcd', 'F98DC606E98D8ED94BC38956117C443B0C948BF20046B5815E8C81DEB7FF5700'),
(29, 'xyz', 2, 'xyz', 'AEC6F31ABF055F6F6C7AC6144F9FBF04DA2D84D7C15CB4C2CCD24458E65512F8');

-- --------------------------------------------------------

--
-- Table structure for table `user_types`
--

CREATE TABLE `user_types` (
  `usertype_id` int(1) NOT NULL,
  `user_type` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_types`
--

INSERT INTO `user_types` (`usertype_id`, `user_type`) VALUES
(1, 'Administrator'),
(2, 'Cashier'),
(3, 'Customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `inventory`
--
ALTER TABLE `inventory`
  ADD PRIMARY KEY (`inv_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`prod_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_types`
--
ALTER TABLE `user_types`
  ADD PRIMARY KEY (`usertype_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `inventory`
--
ALTER TABLE `inventory`
  MODIFY `inv_id` int(3) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `prod_id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `user_types`
--
ALTER TABLE `user_types`
  MODIFY `usertype_id` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
