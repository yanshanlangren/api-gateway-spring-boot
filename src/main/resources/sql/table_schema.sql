create table dev.stock
(
    id             bigint unsigned not null auto_increment comment 'id',
    create_time    datetime       default current_timestamp comment '创建时间',
    data_time      datetime       default current_timestamp comment '股票日期',
    now_price      numeric(10, 4) default 0 comment '最新价',
    fluctuation    numeric(10, 4) default 0 comment '涨跌幅',
    change_amount  numeric(10, 4) default 0 comment '涨跌额',
    trade_amount bigint unsigned default 0 comment '成交量(手)',
    trade_volume numeric(20, 4) default 0 comment '成交额',
    amplitude      numeric(10, 4) default 0 comment '振幅',
    change_rate    numeric(10, 4) default 0 comment '换手率',
    per_dynamic    numeric(10, 4) default 0 comment '市盈率(动态)',
    quantity_ratio numeric(10, 4) default 0 comment '量比',
    code           varchar(50)    default '' comment '股票代码',
    stock_type     tinyint        default 0 comment '股票类型',
    name           varchar(50)    default '' comment '股票名称',
    today_high     numeric(10, 4) default 0 comment '最高',
    today_low      numeric(10, 4) default 0 comment '最低',
    today_start    numeric(10, 4) default 0 comment '今开',
    yesterday_end  numeric(10, 4) default 0 comment '昨收',
    pbr            numeric(10, 4) default 0 comment '市净率',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8;