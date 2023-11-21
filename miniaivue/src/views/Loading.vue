<template>

  <!-- Section CTA -->
  <section>
    <!-- CTA Container -->
    <div class="mx-auto w-full max-w-7xl px-5 py-16 md:px-10 md:py-24 lg:py-32">
      <!-- CTA Content -->
      <div class="flex w-full flex-col items-center bg-[#f2f2f7] px-6 py-16 text-center md:py-24 lg:py-32">
        <!-- CTA Title -->
        <div class="mb-8 flex-col">
          <h2 class="mb-4 text-3xl font-bold md:text-5xl">MiniAi提高你的生产力</h2>
          <p class="mx-auto max-w-lg text-[#647084]">支持流式传输，ai绘画，场景预设，云端同步...</p>
        </div>
        <!-- CTA Button -->
        <router-link to="/register" class="mb-4 flex flex-row items-center bg-[#276ef1] px-8 py-4 font-semibold text-white transition [box-shadow:rgb(171,_196,245)-8px_8px] hover:[box-shadow:rgb(171,_196,_245)_0px_0px]">
          <router-link to="/register" exact class="mr-6 font-bold">开始</router-link>
          <svg fill="currentColor" class="h-4 w-4 flex-none" viewBox="0 0 20 21" xmlns="http://www.w3.org/2000/svg">
            <title>Arrow Right</title>
            <polygon points="16.172 9 10.101 2.929 11.515 1.515 20 10 19.293 10.707 11.515 18.485 10.101 17.071 16.172 11 0 11 0 9"></polygon>
          </svg>
        </router-link>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Loading"
}
</script>

<style scoped>

</style>