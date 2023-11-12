<template>
  <div class="bg-slate-50">
    <section>
      <div class="h-auto  bg-black text-white">
        <nav class="font-inter  h-auto  lg:relative lg:top-0">
          <div class="flex flex-col px-6 py-6 lg:flex-row lg:items-center lg:justify-between lg:px-10 lg:py-4 xl:px-20">
            <a href="#">
<!--              <img class="relative left-5 lg:inset-0" width="75" height="24" viewBox="0 0 75 24" fill="none" xmlns="http://www.w3.org/2000/svg">-->
<!--<!-              -->
<!--              </img>-->
            </a>
            <div class="mt-14 flex flex-col space-y-8 lg:mt-0 lg:flex lg:flex-row lg:space-x-1 lg:space-y-0">
              <router-link to="/" class="font-inter rounded-lg hover:text-[#c9fd02] lg:px-6 lg:py-4">首页</router-link>
              <router-link to="/chat/sendMessage" class="font-inter rounded-lg hover:text-[#c9fd02] lg:px-6 lg:py-4">对话</router-link>
              <router-link to="/price" class="font-inter rounded-lg hover:text-[#c9fd02] lg:px-6 lg:py-4">定价</router-link>
              <router-link to="/faq" class="font-inter rounded-lg hover:text-[#c9fd02] lg:px-6 lg:py-4">常见问题</router-link>
              <router-link to="/about" class="font-inter rounded-lg pb-8 hover:text-[#c9fd02] lg:px-6 lg:py-4 lg:pb-0">时间线</router-link>
            </div>
            <div class="flex flex-col space-y-8 lg:flex lg:flex-row lg:space-x-3 lg:space-y-0">
              <router-link to="/register" class="inline-block rounded-full bg-white px-5 py-3 text-center font-bold text-black transition hover:border-black hover:bg-[#c9fd02]">去注册</router-link>
            </div>
          </div>
        </nav>
      </div>
      <router-view/>
      <footer>
        <Footer/>
      </footer>
    </section>
  </div>
</template>

<script>
import Footer from "@/views/Footer";

export default {
  name: "UserLayout",
  components: {Footer},
}
</script>

<style scoped>
@import '../assets/css/main.css';
</style>