<template>
<!--  <div>-->
<!--    <nav>-->
<!--      <router-link to="/">Home</router-link>-->
<!--      <router-link to="/about">About</router-link>-->
<!--    </nav>-->
<!--    <router-view></router-view>-->
<!--  </div>-->



  <!-- Navbar Dropdown 1 row -->
  <div class="bg-slate-50">


  <section>
    <div class="h-auto w-screen bg-black text-white">
      <!-- NAVBAR -->
      <nav class="font-inter mx-auto h-auto w-full max-w-[1600px] lg:relative lg:top-0" x-data="{isOpen: false, menuOne:false}">
        <!-- CONTAINER -->
        <div class="flex flex-col px-6 py-6 lg:flex-row lg:items-center lg:justify-between lg:px-10 lg:py-4 xl:px-20">
          <!-- SVG LOGO - YOU CAN REPLACE THIS -->
          <a href="#">
            <img src="https://uploads-ssl.webflow.com/646f65e37fe0275cfb808405/646f66cdeeb4ddfdae25a26c_%5BA%5D--Navbar%20Brand.png" alt="" class="inline-block max-h-6" />
          </a>
          <!-- MENU CONTENT 1 -->
          <div class="mt-14 flex flex-col space-y-8 lg:mt-0 lg:flex lg:flex-row lg:space-x-1 lg:space-y-0" x-bind:class="isOpen ? 'show' : 'hidden'">
            <!-- DROPDOWN -->
<!--            <div class="relative flex flex-col">-->
<!--              <a x-on:click.prevent="menuOne = !menuOne" href="#" class="flex flex-row rounded-lg hover:text-[#c9fd02] lg:px-6 lg:py-4" x-bind:class="menuOne ? ' text-[#c9fd02] ' : 'text-white  ' ">首页 <svg x-bind:class="menuOne ? 'rotate-180' : 'rotate-0' " class="fill-current transition" style="width: 24px; height: 24px" viewBox="0 0 24 24">-->
<!--                <path d="M7.41,8.58L12,13.17L16.59,8.58L18,10L12,16L6,10L7.41,8.58Z" />-->
<!--              </svg>-->
<!--              </a>-->

<!--            </div>-->
            <router-link to="/" class="font-inter rounded-lg hover:text-[#c9fd02] lg:px-6 lg:py-4">首页</router-link>
            <router-link to="/price" class="font-inter rounded-lg hover:text-[#c9fd02] lg:px-6 lg:py-4">定价</router-link>
            <a href="#" class="font-inter rounded-lg hover:text-[#c9fd02] lg:px-6 lg:py-4">常见问题</a>
            <router-link to="/about" class="font-inter rounded-lg pb-8 hover:text-[#c9fd02] lg:px-6 lg:py-4 lg:pb-0">关于</router-link>
          </div>
          <!-- MENU CONTENT 2 -->
          <div class="flex flex-col space-y-8 lg:flex lg:flex-row lg:space-x-3 lg:space-y-0" x-bind:class="isOpen ? 'show' : 'hidden'">
            <router-link to="/login" target="_blank" class="inline-block rounded-full bg-white px-5 py-3 text-center font-bold text-black transition hover:border-black hover:bg-[#c9fd02]">去注册</router-link>
          </div>

<!--          &lt;!&ndash; BURGER MENU &ndash;&gt;-->
<!--          <a href="#" class="absolute right-5 lg:hidden" x-on:click.prevent="isOpen = !isOpen">-->
<!--            <svg width="1.25rem" height="1rem" viewBox="0 0 20 16" fill="none" xmlns="http://www.w3.org/2000/svg">-->
<!--              <path d="M19 7H1C0.447715 7 0 7.44772 0 8C0 8.55228 0.447715 9 1 9H19C19.5523 9 20 8.55228 20 8C20 7.44772 19.5523 7 19 7Z" fill="currentColor"></path>-->
<!--              <path d="M19 0H7C6.44772 0 6 0.447715 6 1C6 1.55228 6.44772 2 7 2H19C19.5523 2 20 1.55228 20 1C20 0.447715 19.5523 0 19 0Z" fill="currentColor"></path>-->
<!--              <path d="M19 14H11C10.4477 14 10 14.4477 10 15C10 15.5523 10.4477 16 11 16H19C19.5523 16 20 15.5523 20 15C20 14.4477 19.5523 14 19 14Z" fill="currentColor"></path>-->
<!--            </svg>-->
<!--          </a>-->
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
  name: 'App',
  components: {Footer},
}
</script>

<style>
@import './assets/css/main.css';
</style>
