var app = new Vue({
    el: '#ShowDB',
    data: {
        // 数据源信息
        dataSorucesInfo: [],
        // 当前选择的数据源
        currentDataSource: null,
        currentDataSourceInfo: null,
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
    },
    methods: {
        // 数据源信息
        dsInfo() {
            const that = this
            axios.get('/showdb/config/dsInfo').then(function (res) {
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                var res = res.data.data;
                that.dataSorucesInfo = res;
                that.currentDataSource = that.dataSorucesInfo[0].beanName
                that.currentDataSourceInfo = that.dataSorucesInfo[0]
            })
        },
        // 查询表集合名称
        tableNames() {
            const that = this
            axios.get('/showdb/struct/' + this.currentDataSource + '/all').then(function (res) {
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                var res = res.data.data;
                that.tableNameList = res;
            })
        },
        // 获取单表信息-模糊查询-分页
        getByTableName(tableStructSize, tableStructPageNumber, table) {
            const that = this
            axios.get('/showdb/struct/' + this.currentDataSource + '/' + this.tableStructSize + '/' + this.tableStructPageNumber + '/' + table).then(function (res) {
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                that.tableStruct = res.data.data.tableStructs
                that.total = res.data.data.total
                document.body.scrollTop = document.documentElement.scrollTop = 0;
            })
        },

        //=========================分页查询表信息==================
        tableStructByPage(tableStructSize, tableStructPageNumber) {
            const that = this
            axios.get('/showdb/struct/' + this.currentDataSource + '/' + tableStructSize + '/' + tableStructPageNumber).then(function (res) {
                if (res.data.code !== 200) {
                    alert(res.data.msg);
                    return;
                }
                that.tableStruct = res.data.data.tableStructs
                that.total = res.data.data.total
            })
        },
        handleCurrentChange(number) {
            this.loadingTables = true;
            if (this.queryTableName != '' || this.queryTableName != null) {
                this.getByTableName(this.tableStructSize, number, this.queryTableName);
                this.loadingTables = false;
                return;
            }
            this.tableStructByPage(this.tableStructSize, number);
            document.body.scrollTop = document.documentElement.scrollTop = 0;
            this.loadingTables = false;
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
        dataSourceChange(dsBeanName) {
            // var that = this
            // this.loadingDataSource = true;
            // this.loadingTables = true;
            // axios.get('/showdb/config/switchDataSource/' + dsBeanName).then(function (res) {
            //     if (res.data.code !== 200) {
            //         alert(res.data.msg);
            //         return;
            //     }
            //     that.currentDataSource = dsBeanName;
            //     that.tableStructByPage(that.tableStructSize, that.tableStructPageNumber);
            //     that.tableNames();
            //     that.dsInfo();
            // })
            // this.loadingDataSource = false;
            // this.loadingTables = false;
        },

        /**
         * 缓存清理
         */
        cleanCache() {
            console.log("111")
            // this.loadingTables = true;
            // this.loadingDataSource = true;
            // var that = this;
            // axios.delete('/showdb/config/cleanCache').then(function (res) {
            //     if (res.data.code !== 200) {
            //         alert(res.data.msg);
            //         return;
            //     }
            //     that.loadingTables = true;
            //     that.loadingDataSource = true;
            // })
        },

        starIt() {
            window.open("https://github.com/Cocowwy/ShowDB");
        }
    },

    // 加载页面初始化数据
    mounted: function () {
        this.loadingTables = true;
        this.loadingDataSource = true;

        this.dsInfo();
        this.tableStructByPage(this.tableStructSize, this.tableStructPageNumber);
        this.tableNames();

        this.loadingTables = false;
        this.loadingDataSource = false;

        console.log('作者：Cocowwy  Github:https://github.com/Cocowwy/ShowDB')
    }
})