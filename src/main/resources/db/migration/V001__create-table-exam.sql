CREATE TABLE `exam` (
  `id` bigint(20) NOT NULL auto_increment,
  `candidate_id` bigint(20) DEFAULT NULL,
  `availability_id` bigint(20) DEFAULT NULL,
  `datescheduling` timestamp NOT NULL,
  `typecertification` varchar(20) NOT NULL,
   `codesubscription` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = latin1