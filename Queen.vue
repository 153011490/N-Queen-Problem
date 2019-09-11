<template>
  <div>
    <el-form :inline="true">
      <el-form-item label="阶数">
        <el-input v-model="dimension"
                  @input="getAll"
                  style="width:200px"
                  placeholder="请输入阶数"></el-input>
      </el-form-item>
    </el-form>
    <div v-if="resultList.length > 0">
      <el-row v-for="i in parseInt(dimension)"
              style="margin-left:35%"
              :key="i">
        <el-col :span="0.1"
                v-for="j in parseInt(dimension)"
                :key="j">
          <el-card class="box-card"
                   v-if="(i+j)%2==0"
                   style="background-color:lightgrey">
            <div v-if="resultList[currentResult-1][i-1] == j-1">👑</div>
            <div v-else> </div>
          </el-card>
          <el-card class="box-card"
                   v-else>
            <div v-if="resultList[currentResult-1][i-1] == j-1">👑</div>
            <div v-else> </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div v-else>无解</div>
    <div>共有<el-tag type="success">{{resultList.length}}</el-tag>个解，当前是第
      <el-tag type="success">{{currentResult}}</el-tag>个解。
    </div>
  </div>
</template>

 <script>
import { getResultList } from "@/api/queen.js"
export default {
  data() {
    return {
      dimension: 10,
      resultList: [],
      currentResult: 1,
      timer: null
    }
  },
  methods: {
    getAll() {
      getResultList(this.dimension).then((response) => {
        this.resultList = response.data
        this.currentResult = 1;
      })
    },
    setTimer() {
      if (this.timer == null) {
        this.timer = setInterval(() => {
          if (this.currentResult > this.resultList.length) {
            this.currentResult = 1;
          } else {
            this.currentResult++;
          }
        }, 200)
      }
    }
  },
  beforeMount() {
    this.getAll()
    this.setTimer()
  },
  created: function () {
    this.getFamilyBase_info()
    // 每次进入界面时，先清除之前的所有定时器，然后启动新的定时器
    clearInterval(this.timer)
    this.timer = null
    this.setTimer()
  },
  destroyed: function () {
    // 每次离开当前界面时，清除定时器
    clearInterval(this.timer)
    this.timer = null
  }
}
</script>

<style>
.text {
  font-size: 14px;
}
.box-card {
  width: 50px;
  height: 50px;
}
</style>