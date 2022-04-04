var app = new Vue({
    el: '#ShowDB',
    data: {
        // 数据源信息
        dataSorucesInfo: [],
        // 当前选择的数据源
        currentDataSource: null,
        currentDataSourceInfo: null,
        // 当前数据源的主从监控信息
        currentDataSourceMasterSlaveInfo: null,
        // 当前数据源所有表名称集合
        tableNameList: [],
        // 搜索框选择值
        queryTableName: '',
        // loadiong..
        loadingTables: true,
        loadingDataSource: false,

        // 分页表结构
        tableStructSize: 5,
        tableStructPageNumber: 1,
        tableStruct: null,
        total: 0,

        // dialog展示框
        // 表sql创建语句
        createStatementDialog: false,
        createStatementContent: null,
        createStatementTableName: null,
        // Java代码生成
        createJavaCodeDialog: false,
        createJavaCodeContent: null,
        createJavaCodeTableName: null,
        // 表详细信息
        tableDetailDialog: false,
        tableDetailTableName: null,
        tableDetailInformation: null,
    },
    methods: {
        // 数据源信息
        dsInfo() {
            const that = this
            this.loadingOpen();
            axios.get('/showdb/config/dsInfo').then(function (res) {
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                var res = res.data.data;
                that.dataSorucesInfo = res;
                that.currentDataSource = that.dataSorucesInfo[0].beanName
                that.currentDataSourceInfo = that.dataSorucesInfo[0]

                that.tableStructByPage(that.tableStructSize, that.tableStructPageNumber);
                that.tableNames();
                that.slaveInfo();
                that.loadingClose();
            })
        },

        // 数据源切换事件
        dsChange() {
            for (var i = 0; i < this.dataSorucesInfo.length; i++) {
                if (this.dataSorucesInfo[i].beanName === this.currentDataSource) {
                    this.currentDataSourceInfo = this.dataSorucesInfo[i];
                    break;
                }
            }
            this.tableStructByPage(this.tableStructSize, this.tableStructPageNumber);
            this.tableNames();
            this.slaveInfo();
        },

        // 查询表集合名称
        tableNames() {
            const that = this
            this.loadingOpen();
            axios.get('/showdb/struct/' + this.currentDataSource + '/all').then(function (res) {
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                var res = res.data.data;
                that.tableNameList = res;
                that.loadingClose();
            })
        },
        // 获取单表信息-模糊查询-分页
        getByTableName(tableStructSize, tableStructPageNumber, table) {
            const that = this
            this.loadingOpen();
            axios.get('/showdb/struct/' + this.currentDataSource + '/' + this.tableStructSize + '/' + this.tableStructPageNumber + '/' + table).then(function (res) {
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                that.tableStruct = res.data.data.tableStructs
                that.total = res.data.data.total
                document.body.scrollTop = document.documentElement.scrollTop = 0;
                that.loadingClose();
            })
        },

        //=========================分页查询表信息==================
        tableStructByPage(tableStructSize, tableStructPageNumber) {
            const that = this
            this.loadingOpen();
            axios.get('/showdb/struct/' + this.currentDataSource + '/' + tableStructSize + '/' + tableStructPageNumber).then(function (res) {
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                that.tableStruct = res.data.data.tableStructs
                that.total = res.data.data.total;
                that.loadingClose();
            })
        },
        handleCurrentChange(number) {
            if (this.queryTableName != '' || this.queryTableName != null) {
                this.getByTableName(this.tableStructSize, number, this.queryTableName);
                this.loadingTables = false;
                return;
            }
            this.tableStructByPage(this.tableStructSize, number);
            document.body.scrollTop = document.documentElement.scrollTop = 0;
        },

        // ===========================搜索框=======================
        // 选择框选择监听
        handleSelect(value) {
            this.tableStructSize = 5;
            this.tableStructPageNumber = 1;
            if (this.queryTableName !== undefined) {
                // 适配直接输入获取值
                this.getByTableName(this.tableStructSize, this.tableStructPageNumber, this.queryTableName);
            } else {
                // 适配选择的时候获取值，选择的时候为 undefined
                this.queryTableName = value;
                this.getByTableName(this.tableStructSize, this.tableStructPageNumber, this.queryTableName);
            }
        },
        querySearch(queryString, cb) {
            var tableNameList = this.tableNameList;
            var results = queryString ? tableNameList.filter(this.createFilter(queryString)) : tableNameList;
            // 调用 callback 返回建议列表的数据
            cb(results);
        },
        createFilter(queryString) {
            return (i) => {
                return (i.indexOf(queryString) >= 0);
            };
        },

        // 数据源切换
        dataSourceChange() {
            this.dsInfo();
        },

        loadingOpen() {
            this.loadingTables = true;
            this.loadingDataSource = true;
        },

        loadingClose() {
            this.loadingTables = false;
            this.loadingDataSource = false;
        },

        /**
         * 缓存清理
         */
        cleanCache() {
            var that = this;
            axios.delete('/showdb/config/cleanCache').then(function (res) {
                that.loadingOpen()
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                that.loadingClose()
                const h = that.$createElement;
                that.$notify({
                    title: '成功',
                    duration: 3000,
                    message: h('i', {style: 'color: teal'}, '缓存清理成功')
                });
            })
        },

        /**
         * 表创建语句
         */
        tableCreateStatement(table) {
            var that = this;
            axios.get('/showdb/struct/' + this.currentDataSource + '/create/' + table).then(function (res) {
                that.loadingOpen()
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                that.createStatementDialog = true
                that.createStatementContent = res.data.data
                that.createStatementTableName = table
                that.loadingClose()
            });
        },

        /**
         * Java实体生成
         */
        tableJavaCode(table) {
            var that = this;
            axios.get('/showdb/struct/' + this.currentDataSource + '/java/' + table).then(function (res) {
                that.loadingOpen()
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                that.createJavaCodeDialog = true
                that.createJavaCodeContent = res.data.data
                that.createJavaCodeTableName = table
                that.loadingClose()
            });
        },

        /**
         * 表的详细信息
         */
        tableDetailInfo(table) {
            var that = this
            this.loadingOpen()
            axios.get('/showdb/struct/' + this.currentDataSource + '/' + table + '/detailInfo').then(function (res) {
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                that.tableDetailDialog = true
                that.tableDetailTableName = table
                that.tableDetailInformation = res.data.data
                that.loadingClose()
            });
        },

        /**
         * 主从监控
         */
        slaveInfo() {
            var that = this;
            axios.get('/showdb/monitor/' + this.currentDataSource + '/masterSlaveInfo').then(function (res) {
                that.loadingOpen()
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                that.currentDataSourceMasterSlaveInfo = res.data.data
                that.loadingClose()
            });
        },

        starIt() {
            window.open("https://github.com/Cocowwy/ShowDB");
        }
    },

    // 加载页面初始化数据
    mounted: function () {
        this.dsInfo();
        console.log('作者：Cocowwy  Github：https://github.com/Cocowwy/ShowDB\n'
            + '███████╗██╗  ██╗ ██████╗ ██╗    ██╗██████╗ ██████╗ \n' +
            '██╔════╝██║  ██║██╔═══██╗██║    ██║██╔══██╗██╔══██╗\n' +
            '███████╗███████║██║   ██║██║ █╗ ██║██║  ██║██████╔╝\n' +
            '╚════██║██╔══██║██║   ██║██║███╗██║██║  ██║██╔══██╗\n' +
            '███████║██║  ██║╚██████╔╝╚███╔███╔╝██████╔╝██████╔╝\n' +
            '╚══════╝╚═╝  ╚═╝ ╚═════╝  ╚══╝╚══╝ ╚═════╝ ╚═════╝')
    }
})
