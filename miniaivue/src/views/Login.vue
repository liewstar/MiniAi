<template>

  <section>
    <!-- Container -->
    <div class="mx-auto w-full max-w-3xl px-5 py-16 md:px-10 md:py-24 lg:py-32">
      <!-- Component -->
      <div class="mx-auto max-w-xl bg-[#f2f2f7] px-8 py-12 text-center">
        <div class="mx-auto w-full max-w-[400px]">
<!--          <a href="#" class="flex w-full max-w-full justify-center rounded-md bg-black py-3 text-white">-->
<!--            <img src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a947090e6cf87_GoogleLogo.svg" alt="" class="mr-4 inline-block" />-->
<!--            <p class="text-sm sm:text-base">Sign up with Google</p>-->
<!--          </a>-->
          <!-- Divider -->
          <!-- Form -->
          <div class="mx-auto mb-4 max-w-[400px] pb-4">
            <form name="wf-form-password" method="get">
              <div class="relative">
                <img alt="" src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a9455fae6cf89_EnvelopeSimple.svg" class="absolute left-[5%] top-[26%] inline-block" />
                <input type="email" class="mb-4 block h-9 w-full rounded-md border border-solid border-black px-3 py-6 pl-14 text-sm text-[#333333]" placeholder="Email Address" required="" />
              </div>
              <div class="relative mb-4">
                <img alt="" src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a946794e6cf8a_Lock-2.svg" class="absolute left-[5%] top-[26%] inline-block" />
                <input type="password" class="mb-4 block h-9 w-full rounded-md border border-solid border-black px-3 py-6 pl-14 text-sm text-[#333333]" placeholder="Password (min 8 characters)" required="" />
              </div>
              <input type="submit" value="注册" class="mt-4 inline-block w-full cursor-pointer items-center rounded-md bg-black px-6 py-3 text-center font-semibold text-white" />
            </form>
          </div>
          <p class="text-sm text-[#636262]">没有账号? <router-link to="/register" class="font-bold text-[#0b0b1f]">去注册</router-link>
          </p>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Login"
}
</script>

<style scoped>

</style>