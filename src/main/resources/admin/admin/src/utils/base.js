const base = {
    get() {
        return {
            url : "http://localhost:8080/work_study/",
            name: "work_study",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/work_study/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "勤工助学管理系统"
        } 
    }
}
export default base
