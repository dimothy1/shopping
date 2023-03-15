<template>
  <div class="footer">
    <div class="footer-logo">
      <a href="https://www.neusoft.com/cn/"><img src="https://www.neusoft.com/cn/upload/img/logo.svg" alt="东软集团"></a>
      <p>东软睿购</p>
    </div>

    <div class="copyright">Copyright ©2019 <span class="domain">东软睿购商城</span> All Rights Reserved.</div>
  </div>
</template>

<script>
export default {
  name: "NavFooter"
}
</script>

<style lang="scss" scoped>
.footer{
  height:234px;
  border-top:4px solid #FF6600;
  background-color:#333333;
  color:#999999;
  font-size:16px;
  text-align:center;
  .footer-logo{
    margin-top:46px;
    margin-bottom:31px;
    img{
      width:53px;
      height:53px;
    }
  }
  .footer-link{
    a{
      color:#999999;
      display:inline-block;
    }
    span{
      margin:0 10px;
    }
    margin-bottom:13px;
  }
  .copyright{
    .domain{
      color:#FF6600;
    }
  }
}
</style>