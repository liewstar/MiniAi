<template>
  <section>
    <div class="mx-auto w-full max-w-3xl px-5 py-16 md:px-10 md:py-24 lg:py-32">
      <div class="mx-auto max-w-xl bg-[#f2f2f7] px-8 py-12 text-center">
        <h2 class="text-3xl font-bold md:text-5xl">开启14天免费试用</h2>
        <p class="mx-auto my-5 max-w-md text-sm text-[#636262] sm:text-base lg:mb-8">让Ai惠及每一个人</p>
        <div class="mx-auto w-full max-w-[400px]">

          <!-- Form -->
          <div class="mx-auto mb-4 max-w-[400px] pb-4">
            <form name="wf-form-password">
              <div class="relative">
                <img alt="" src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a9455fae6cf89_EnvelopeSimple.svg" class="absolute left-[5%] top-[26%] inline-block" />
                <input v-model="username" type="text" class="mb-4 block h-9 w-full rounded-md border border-solid border-black px-3 py-6 pl-14 text-sm text-[#333333]" placeholder="输入账号"/>
              </div>
              <div class="relative mb-4">
                <img alt="" src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a946794e6cf8a_Lock-2.svg" class="absolute left-[5%] top-[26%] inline-block" />
                <input v-model="password" placeholder="输入密码" type="password" class="mb-4 block h-9 w-full rounded-md border border-solid border-black px-3 py-6 pl-14 text-sm text-[#333333]"/>
              </div>
              <input @click="register" value="注册" class="mt-4 inline-block w-full cursor-pointer items-center rounded-md bg-black px-6 py-3 text-center font-semibold text-white" />
            </form>
          </div>
          <p class="text-sm text-[#636262]">已经有账号? <router-link to="/login" class="font-bold text-[#0b0b1f]">去登录</router-link>
          </p>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import api from "@/api"
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Register.vue",
  data(){
    return{
      username:'',
      password:''
    }
  },
  methods:{
    register() {
      const registerData = {
        username:this.username,
        password:this.password
      }
      api.post("/users/register",registerData,{
        headers:{
          'Content-Type':'application/json'
        }
      })
      .then(response => {
        if(response.code === 200) {
          this.$message({
            message:'注册成功',
            type:"success"
          })
          this.$router.push("/login")
        }else {
          this.$message({
            message: '注册失败，账号已存在',
            type: "error"
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>