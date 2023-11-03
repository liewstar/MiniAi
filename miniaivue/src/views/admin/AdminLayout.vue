<template>
  <div class="bg-slate-50">
    <section>
      <div class="h-auto  bg-black text-white">
        <nav class="font-inter  h-auto  lg:relative lg:top-0">
          <div class="flex flex-col px-6 py-6 lg:flex-row lg:items-center lg:justify-between lg:px-10 lg:py-4 xl:px-20">
            <a href="#">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-code">
                <path d="M16 18l6-6-6-6M8 6l-6 6 6 6"></path>
              </svg>
            </a>
            <div class="mt-14 flex flex-col space-y-8 lg:mt-0 lg:flex lg:flex-row lg:space-x-1 lg:space-y-0">
              <router-link to="/" class="font-inter rounded-lg hover:text-[#c9fd02] lg:px-6 lg:py-4">用户管理</router-link>
              <router-link to="/price" class="font-inter rounded-lg hover:text-[#c9fd02] lg:px-6 lg:py-4">订单管理</router-link>
              <a href="#" class="font-inter rounded-lg hover:text-[#c9fd02] lg:px-6 lg:py-4">数据统计</a>
              <router-link to="/about" class="font-inter rounded-lg pb-8 hover:text-[#c9fd02] lg:px-6 lg:py-4 lg:pb-0">关于</router-link>
            </div>
            <div class="flex flex-col space-y-8 lg:flex lg:flex-row lg:space-x-3 lg:space-y-0">
              <router-link to="/register" class="inline-block rounded-full bg-white px-5 py-3 text-center font-bold text-black transition hover:border-black hover:bg-[#c9fd02]">去网站</router-link>
            </div>
          </div>
        </nav>
      </div>
      <router-view></router-view>
      <footer>
        <Footer/>
      </footer>
    </section>
  </div>
</template>

<script>
import Footer from "@/views/Footer";

export default {
  name: "AdminLayout",
  components: {Footer},
}
</script>

<style scoped>
@import "/src/assets/css/main.css";
</style>