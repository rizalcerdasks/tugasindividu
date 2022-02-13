-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 13, 2022 at 11:56 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inixtraining`
--

-- --------------------------------------------------------

--
-- Table structure for table `detail_kelas`
--

CREATE TABLE `detail_kelas` (
  `id_detail_kls` int(11) NOT NULL,
  `id_kls` int(11) NOT NULL,
  `id_pst` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `detail_kelas`
--

INSERT INTO `detail_kelas` (`id_detail_kls`, `id_kls`, `id_pst`) VALUES
(1, 1, 3),
(2, 1, 2),
(3, 1, 1),
(4, 20, 3),
(5, 20, 2),
(6, 20, 1),
(7, 2, 4),
(8, 3, 4),
(9, 4, 5),
(10, 4, 6),
(11, 5, 5),
(12, 5, 6),
(13, 5, 7),
(14, 5, 8),
(15, 19, 7),
(16, 19, 8),
(17, 6, 9),
(18, 6, 10),
(19, 18, 9),
(20, 18, 10),
(21, 7, 11),
(22, 7, 12),
(23, 17, 11),
(24, 17, 12),
(25, 8, 13),
(26, 8, 14),
(27, 16, 13),
(28, 16, 14),
(29, 9, 15),
(30, 9, 16),
(31, 15, 15),
(32, 15, 16),
(33, 10, 17),
(34, 10, 18),
(35, 14, 17),
(36, 14, 18),
(37, 11, 19),
(38, 11, 20),
(39, 13, 19),
(40, 13, 20),
(41, 12, 3),
(42, 12, 2),
(43, 12, 1),
(44, 2, 4),
(45, 3, 4),
(46, 12, 5),
(47, 12, 6),
(48, 13, 5),
(49, 13, 6),
(50, 1, 15),
(51, 1, 16),
(52, 21, 21),
(53, 22, 22),
(54, 23, 23),
(55, 24, 24),
(62, 3, 4),
(64, 4, 1),
(65, 5, 1),
(67, 2, 5),
(68, 5, 4),
(69, 3, 1),
(71, 25, 25),
(73, 25, 2),
(74, 1, 22),
(75, 25, 8),
(76, 25, 22),
(77, 25, 21),
(78, 25, 4),
(81, 25, 3),
(82, 25, 6),
(83, 25, 12),
(84, 25, 10),
(85, 24, 1);

-- --------------------------------------------------------

--
-- Table structure for table `instruktur`
--

CREATE TABLE `instruktur` (
  `id_ins` int(11) NOT NULL,
  `nama_ins` varchar(100) NOT NULL,
  `email_ins` varchar(50) NOT NULL,
  `hp_ins` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `instruktur`
--

INSERT INTO `instruktur` (`id_ins`, `nama_ins`, `email_ins`, `hp_ins`) VALUES
(1, 'Abdul Rachman', 'abdul@gmail.com', '08237192312'),
(2, 'Achmad Fadjar', 'fadjar@gmail.com', '08143192312'),
(3, 'Ade R. Syarief', 'ade@gmail.com', '08266542312'),
(4, 'Adi Sumito', 'adi@gmail.com', '08237192312'),
(5, 'Adimitra Baratama Nusantara', 'adimitra@gmail.com', '08335678312'),
(6, 'Adji Muljo Teguh', 'adji@gmail.com', '08222331234'),
(7, 'Adi Achmad Drajat', 'adri@gmail.com', '08983242343'),
(8, 'Adryansyah', 'adryansyah@gmail.com', '08237195532'),
(9, 'Ago Harlim', 'ago@gmail.com', '08234432345'),
(10, 'Agung Salim', 'agung@gmail.com', '08344692312'),
(11, 'Baldeo Singh', 'baldeo@gmail.com', '08237177652'),
(12, 'Bambang Tijoso Tedjokusumo', 'bambang@gmail.com', '08237554312'),
(13, 'Bayu Irianto', 'bayu@gmail.com', '08235532512'),
(14, 'Belinda Natalia Tanoko', 'belinda@gmail.com', '08288567443'),
(15, 'Benny Tenges', 'benny@gmail.com', '08783373487'),
(16, 'Bernadette Ruth Irawati', 'bernadette@gmail.com', '08665327654'),
(17, 'Celin Tanardi', 'celin@gmail.com', '08766628391'),
(18, 'Chin Chin Chandera', 'chin@gmail.com', '08122579094'),
(19, 'Christianto', 'christianto@gmail.co', '08979646274'),
(20, 'Dani Neu', 'dani@gmail.com', '08988836802'),
(21, 'Yogi Ariyanto', 'yogi@gmail.com', '08393022873'),
(22, 'Sri Mulyani', 'sri@gmail.com', '08333223321'),
(23, 'Nur Jannah', 'nur@gmail.com', '0833188839'),
(24, 'Endang Sukamto', 'endang@gmail.com', '0848829202'),
(25, 'Tri Manda', 'tri@gmail.com', '08367489932');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `id_kls` int(11) NOT NULL,
  `tgl_mulai_kls` date NOT NULL,
  `tgl_akhir_kls` date NOT NULL,
  `id_ins` int(11) NOT NULL,
  `id_mat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`id_kls`, `tgl_mulai_kls`, `tgl_akhir_kls`, `id_ins`, `id_mat`) VALUES
(1, '2022-01-01', '2022-01-08', 1, 1),
(2, '2022-02-02', '2022-02-09', 2, 2),
(3, '2022-03-10', '2022-02-17', 3, 3),
(4, '2022-04-11', '2022-04-18', 4, 4),
(5, '2022-05-12', '2022-05-19', 5, 5),
(6, '2022-06-13', '2022-06-20', 6, 6),
(7, '2022-07-01', '2022-07-08', 7, 7),
(8, '2022-08-02', '2022-08-09', 8, 8),
(9, '2022-09-03', '2022-09-10', 9, 9),
(10, '2022-10-04', '2022-10-11', 10, 10),
(11, '2022-11-05', '2022-11-12', 11, 11),
(12, '2022-12-06', '2022-12-18', 13, 12),
(13, '2022-03-11', '2022-03-18', 13, 13),
(14, '2022-04-12', '2022-04-19', 14, 14),
(15, '2022-05-13', '2022-05-20', 15, 15),
(16, '2022-06-14', '2022-06-21', 16, 16),
(17, '2022-07-15', '2022-07-22', 17, 17),
(18, '2022-08-16', '2022-08-23', 18, 18),
(19, '2022-09-17', '2022-09-24', 19, 19),
(20, '2022-10-18', '2022-10-25', 20, 20),
(21, '2023-02-12', '2023-03-12', 21, 22),
(22, '2023-03-13', '2023-04-12', 22, 21),
(23, '2023-05-12', '2023-02-12', 23, 23),
(24, '2023-06-13', '2023-06-24', 24, 24),
(25, '2023-08-09', '2023-08-18', 25, 25);

-- --------------------------------------------------------

--
-- Table structure for table `materi`
--

CREATE TABLE `materi` (
  `id_mat` int(11) NOT NULL,
  `nama_mat` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `materi`
--

INSERT INTO `materi` (`id_mat`, `nama_mat`) VALUES
(1, 'Android Java'),
(2, 'Cisco Devnet'),
(3, 'Checkpoint Security'),
(4, 'Palo Alto Security'),
(5, 'Mikrotik Networking'),
(6, 'Cisco ACI'),
(7, 'Python'),
(8, 'Oracle'),
(9, 'Azure'),
(10, 'Microsoft'),
(11, 'Internet of Things'),
(12, 'Cisco Networking'),
(13, 'C++'),
(14, 'Javascript'),
(15, 'PostgreSQL'),
(16, 'MongoDB'),
(17, 'UI/UX'),
(18, 'Cyberark'),
(19, 'Docker'),
(20, 'Data Science'),
(21, 'AI'),
(22, 'Copywriting'),
(23, 'SEO'),
(24, 'Wordpress'),
(25, 'Google Adsense');

-- --------------------------------------------------------

--
-- Table structure for table `peserta`
--

CREATE TABLE `peserta` (
  `id_pst` int(11) NOT NULL,
  `nama_pst` varchar(100) NOT NULL,
  `email_pst` varchar(50) NOT NULL,
  `hp_pst` varchar(20) NOT NULL,
  `instansi_pst` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `peserta`
--

INSERT INTO `peserta` (`id_pst`, `nama_pst`, `email_pst`, `hp_pst`, `instansi_pst`) VALUES
(1, 'Rizal Cerdas', 'rizal@maybank.co.id', '081222591029', 'Maybank'),
(2, 'Kia Dzaky', 'kia@maybank.co.id', '08337829933', 'Maybank'),
(3, 'Abi Praya', 'abi@maybank.co.id', '082362829122', 'Maybank'),
(4, 'Sepli Ariyano', 'sepli@unl.com', '083378292333', 'Universitas Lampung'),
(5, 'Dendika Dwi', 'dika@telu.ac.id', '0823628291231', 'Telkom University'),
(6, 'Lutfiah Intan', 'intan@telu.ac.id', '082362829213', 'Telkom University'),
(7, 'Nasrullah', 'nasrul@pertamina.co.id', '083378292432', 'Pertamina'),
(8, 'Rafiq Alfansa', 'rafiq@pertamina.co.id', '083378299221', 'Pertamina'),
(9, 'Ilham Ahmad', 'ilham@telkomsel.com', '083378291234', 'Telkomsel'),
(10, 'Ghozie Ikhsan', 'ghozie@telkomsel.com', '082362822413', 'Telkomsel'),
(11, 'Defi Permata', 'defi@unsoed.com', '082362829127', 'Unsoed'),
(12, 'Dona Sari', 'dona@unsoed.com', '082362812327', 'Unsoed'),
(13, 'Sari Putra', 'sari@unsoed.com', '08236282317', 'Unsoed'),
(14, 'Rizka Putri', 'rizka@unsoed.com', '082323112327', 'Unsoed'),
(15, 'Coki Muslim', 'coki@bri.com', '08223212327', 'BRI'),
(16, 'Tona Toni', 'tona@bri.com', '082797112327', 'BRI'),
(17, 'Bagas Cimahi', 'bagas@bri.com', '081378112325', 'BRI'),
(18, 'Isna Farih', 'isna@bni.com', '083692011232', 'BNI'),
(19, 'Eta Putri', 'eta@bni.com', '084292011217', 'BNI'),
(20, 'Esra Mugi', 'esra@bni.com', '087392012327', 'BNI'),
(21, 'Brando', 'brando@ufc.com', '08736372822', 'UFC'),
(22, 'Windah Basudara', 'windah@ufc.com', '08726647289', 'UFC'),
(23, 'Pratama Arhan', 'pratama@timnas.com', '08263722938', 'Timnas'),
(24, 'Witan Sulaiman', 'witan@timnas.com', '08936328278', 'Timnas'),
(25, 'Bagas Bagus', 'bagas@timnas.com', '0833622389', 'Timnas');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detail_kelas`
--
ALTER TABLE `detail_kelas`
  ADD PRIMARY KEY (`id_detail_kls`),
  ADD KEY `id_kls` (`id_kls`),
  ADD KEY `id_pst` (`id_pst`);

--
-- Indexes for table `instruktur`
--
ALTER TABLE `instruktur`
  ADD PRIMARY KEY (`id_ins`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`id_kls`),
  ADD KEY `id_ins` (`id_ins`),
  ADD KEY `id_mat` (`id_mat`);

--
-- Indexes for table `materi`
--
ALTER TABLE `materi`
  ADD PRIMARY KEY (`id_mat`);

--
-- Indexes for table `peserta`
--
ALTER TABLE `peserta`
  ADD PRIMARY KEY (`id_pst`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_kelas`
--
ALTER TABLE `detail_kelas`
  MODIFY `id_detail_kls` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT for table `instruktur`
--
ALTER TABLE `instruktur`
  MODIFY `id_ins` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `kelas`
--
ALTER TABLE `kelas`
  MODIFY `id_kls` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `materi`
--
ALTER TABLE `materi`
  MODIFY `id_mat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `peserta`
--
ALTER TABLE `peserta`
  MODIFY `id_pst` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `detail_kelas`
--
ALTER TABLE `detail_kelas`
  ADD CONSTRAINT `detail_kelas_ibfk_1` FOREIGN KEY (`id_kls`) REFERENCES `kelas` (`id_kls`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detail_kelas_ibfk_2` FOREIGN KEY (`id_pst`) REFERENCES `peserta` (`id_pst`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `kelas`
--
ALTER TABLE `kelas`
  ADD CONSTRAINT `kelas_ibfk_1` FOREIGN KEY (`id_ins`) REFERENCES `instruktur` (`id_ins`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `kelas_ibfk_2` FOREIGN KEY (`id_mat`) REFERENCES `materi` (`id_mat`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
