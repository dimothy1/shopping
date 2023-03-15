<template>
  <div class="goods-sale">
    <div  v-for="(item,i) in postItems.length" :key="i">
<!--      <poster :postItem="postItems[i]"></poster>-->
      <goods-box :goodsItem="goodsItems[i]" ></goods-box>
    </div>
  </div>
</template>

<script>
import Poster from "@/components/index/Poster.vue";
import GoodsBox from "@/components/index/GoodsBox.vue";
import request from "@/utils/request";

export default {
  name: "GoodsSale",
  data () {
    return {
      postItems:[],
      goodsItems:[],
      parentId:0,
    }

  },

  // 生命周期钩子函数
  mounted() {
    this.init();
  },
  methods:{
    init(){
      // 获取分类推进、分类推进对应的推进商品
      request.get("/cat/menus").then((res) => {
        let cateMenus = res.data
        for(let i=0;i<cateMenus.length;i++){
          const cate=cateMenus[i];
          this.postItems.push({
            name:cate.name
          });
          this.parentId=cate.id
          request.get("/cat/pros",{
            params:{
              parentId:this.parentId
            }
          }).then((res1)=>{
            let productList=res1.data
            let goods=[];
            for (let j = 0; j < productList.length; j++) {
              const product = productList[j];
              let name=this.postItems[j].name
              goods.push({
                boxTitle:name,
                value:product.title,
                desc:product.sell_point,
                newPrice:product.price,
                src:product.image,
                url:"/#/detail/"
              })
            }
            this.goodsItems.push({
              boxTitle:cate.name,
              url: '/#/searchResult/',
              listData:{
                goods:goods
              }
            })
          })

        }
      });
    }
  },
  components: {
    'Poster': Poster,
    'GoodsBox': GoodsBox
  }
}
</script>

<style lang="less">
.goods-sale {
  position: relative;

  text-align: center;
  width: 1226px;
  height: auto;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
}
.goods-sale *{    box-sizing: border-box;}
</style>