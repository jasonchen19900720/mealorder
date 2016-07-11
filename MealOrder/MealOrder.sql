
CREATE TABLE `user` (
  `userUuid` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL,   
  `phoneNum` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(128) NOT NULL,
  `createTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL default '2015-01-10 00:00:00',
  PRIMARY KEY  (`userUuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `order` (
  `orderid` varchar(32) NOT NULL,
  `username` varchar(64) NOT NULL,   
  `phoneNum` varchar(16) NOT NULL,
  `orderInfo` varchar(1024) NOT NULL,
  `address` varchar(128) NOT NULL,
  `orderStatus` varchar(32) NOT NULL,
  `totalPrice` int(10) NOT NULL default '0',
  `submitTime` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `notes` varchar(128),
  PRIMARY KEY  (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `sCarItem` (
  `id` int NOT NULL auto_increment,
  `userUuid` varchar(32) NOT NULL,   
  `phoneNum` varchar(16) NOT NULL,
  `goodsName` varchar(32) NOT NULL,
  `amount` int(8) NOT NULL,
  `price`int(8) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `dish` (
  `dishId` int NOT NULL auto_increment,
  `dishName` varchar(32) NOT NULL,   
  `imgSrc` varchar(128) NOT NULL,
  `price`int(8) NOT NULL,
  PRIMARY KEY  (`dishId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into `dish` (`dishName`,`imgSrc`,`price`) values('回锅肉','hgr.jpg','30'),('清蒸鲫鱼','qzjy.jpg','50'),('红烧牛肉','hsnr.jpg','60'),('清炖猪蹄','qdzt.jpg','60');

CREATE TABLE `comment` (
  `id` int NOT NULL auto_increment,
  `username` varchar(64) NOT NULL,   
  `dishName` varchar(32) NOT NULL,
  `content` varchar(512) NOT NULL,
  `addTime` timestamp NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




