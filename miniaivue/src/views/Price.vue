<template>

  <section>
    <div class="mx-auto max-w-7xl px-5 py-16 md:px-10 md:py-24 lg:py-32">
      <div class="mx-auto mb-8 max-w-3xl text-center md:mb-12 lg:mb-16">
        <h2 class="text-3xl font-bold md:text-5xl">MiniAi Pricing</h2>
        <p class="mt-4 text-sm text-[#636262] sm:text-base">新注册用户可以享受14天的免费试用</p>
      </div>
      <div class="grid grid-cols-1 gap-8 sm:grid-cols-2 md:grid-cols-3 md:gap-4">
        <div class="b mx-auto flex w-full max-w-md flex-col items-start rounded-md border border-[#cdcdcd] p-8">
          <div class="mb-4 rounded-[4px] bg-black px-4 py-1.5">
            <p class="text-sm font-bold text-white sm:text-sm">基础版</p>
          </div>
          <p class="mb-6 text-base font-light text-[#636262] md:mb-10 lg:mb-12">适合对Ai尝鲜的个人用户</p>
          <h2 class="mb-5 text-3xl font-bold md:mb-6 md:text-5xl lg:mb-8">$99<span class="text-sm font-light sm:text-sm">/year</span>
          </h2>
          <a @click="bug(99)" class="mb-5 w-full rounded-md bg-black px-6 py-3 text-center font-semibold text-white md:mb-6 lg:mb-8">立即购买</a>
          <div class="mt-2 flex items-center">
            <img src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a94a84be6cf60_check-mark.svg" alt="" class="mr-2 inline-block w-4" />
            <p class="text-base">每日限制100条对话</p>
          </div>
        </div>
        <div class="b mx-auto flex w-full max-w-md flex-col items-start rounded-md border border-[#cdcdcd] bg-[#f2f2f7] p-8">
          <div class="mb-4 rounded-[4px] bg-black px-4 py-1.5">
            <p class="text-sm font-bold text-white sm:text-sm">进阶版</p>
          </div>
          <p class="mb-6 text-base font-light text-[#636262] md:mb-10 lg:mb-12">适合需要Ai生产力的个人用户</p>
          <h2 class="mb-5 text-3xl font-bold md:mb-6 md:text-5xl lg:mb-8">$149<span class="text-sm font-light sm:text-sm">/year</span>
          </h2>
          <a @click="bug(149)" class="mb-5 w-full rounded-md bg-black px-6 py-3 text-center font-semibold text-white md:mb-6 lg:mb-8">立即购买</a>
          <div class="mt-2 flex items-center">
            <img src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a94a84be6cf60_check-mark.svg" alt="" class="mr-2 inline-block w-4" />
            <p class="text-base">每日限制500条对话</p>
          </div>
          <div class="mt-2 flex items-center">
            <img src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a94a84be6cf60_check-mark.svg" alt="" class="mr-2 inline-block w-4" />
            <p class="text-base">专属内测群</p>
          </div>
          <div class="mt-2 flex items-center">
            <img src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a94a84be6cf60_check-mark.svg" alt="" class="mr-2 inline-block w-4" />
            <p class="text-base">云端同步</p>
          </div>
        </div>
        <div class="b mx-auto flex w-full max-w-md flex-col items-start rounded-md border border-[#cdcdcd] p-8">
          <div class="mb-4 rounded-[4px] bg-black px-4 py-1.5">
            <p class="text-sm font-bold text-white sm:text-sm">专业版</p>
          </div>
          <p class="mb-6 text-base font-light text-[#636262] md:mb-10 lg:mb-12">适合专业团队或者对Ai重度依赖的个人</p>
          <h2 class="mb-5 text-3xl font-bold md:mb-6 md:text-5xl lg:mb-8">$299<span class="text-sm font-light sm:text-sm">/year</span>
          </h2>
          <a @click="bug(299)" class="mb-5 w-full rounded-md bg-black px-6 py-3 text-center font-semibold text-white md:mb-6 lg:mb-8">立即购买</a>
          <div class="mt-2 flex items-center">
            <img src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a94a84be6cf60_check-mark.svg" alt="" class="mr-2 inline-block w-4" />
            <p class="text-base">无限制时长</p>
          </div>
          <div class="mt-2 flex items-center">
            <img src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a94a84be6cf60_check-mark.svg" alt="" class="mr-2 inline-block w-4" />
            <p class="text-base">专业一对一咨询</p>
          </div>
          <div class="mt-2 flex items-center">
            <img src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a94a84be6cf60_check-mark.svg" alt="" class="mr-2 inline-block w-4" />
            <p class="text-base">专属内测群</p>
          </div>
          <div class="mt-2 flex items-center">
            <img src="https://assets.website-files.com/6458c625291a94a195e6cf3a/6458c625291a94a84be6cf60_check-mark.svg" alt="" class="mr-2 inline-block w-4" />
            <p class="text-base">云端同步</p>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: "Price",
  methods:{
    buy(money) {
      // api.post("/")
      console.log(money)
    }
  }
}
</script>

<style scoped>

</style>