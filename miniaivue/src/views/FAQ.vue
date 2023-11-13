<template>
  <div class="flex justify-center h-screen">
    <el-card class="w-3/4 mt-4 mb-4">
      <div class="flex justify-center">
        <h1>常见问题</h1>
      </div>
      <div>
        <h2>这个网站是什么？</h2>
        <span>这个网站是一个基于ChatGPT的AI问答网站。它使用强大的ChatGPT模型来提供智能问答功能，帮助用户回答各种问题。</span>
        <h2>网站需要付费才能使用吗？</h2>
        <span>不一定。我们提供免费和付费两种使用方式。免费用户可以自行填入OpenAI的API密钥和接口，即可免费使用我们的服务。付费用户可以直接购买套餐，获得更多高级功能和额外的支持。</span>
        <h2>网站的源码开源吗？</h2>
        <span>是的，我们的<el-link type="primary" href="https://github.com/liewstar/MiniAi" target="_blank">网站源码</el-link>目前在GitHub上开源。用户可以免费下载源码进行二次开发，以满足个性化需求或添加新功能。开源代码为用户提供了更大的灵活性和自由度。</span>
        <h2>如何使用网站？</h2>
        <span>使用我们的网站非常简单。首先，您需要注册一个账户。免费用户可以在个人设置中填入OpenAI的API密钥和接口，然后即可开始使用。付费用户可以选择购买适合自己需求的套餐，然后按照指南填入相关信息。一旦设置完成，您就可以在网站上提出问题并获得智能回答。</span>
        <h2>网站支持哪些功能？</h2>
        <span>我们的网站支持以下功能：</span>
        <ul class="list-disc ml-6 mt-2">
          <li>场景预设：您可以选择不同的场景预设，以便ChatGPT更好地理解和回答与该场景相关的问题。</li>
          <li>聊天记录云端同步：您的聊天记录将自动同步到云端，这样您可以随时随地访问和查看之前的对话。</li>
          <li>自定义ChatGPT模型：用户可以根据需求自行选择AI会话使用的ChatGPT模型，以满足不同的需求和场景。</li>
          <li>调整AI行为参数：用户可以自行调整AI的随机性、重复度、话题新鲜度、携带历史消息数以及最大回复限制，以控制AI的回答行为和风格。</li>
        </ul>
      </div>
    </el-card>
  </div>
</template>
<script>
export default {
  name: "FAQ"
}
</script>

<style scoped>
h2 {
  font-size: 1.5rem;
  font-weight: bold;
  margin-top: 1.5rem;
  margin-bottom: 0.5rem;
}
h1 {
  font-size: 2.5rem;
  font-weight: bold;
  margin-top: 1.5rem;
  margin-bottom: 0.5rem;
}
</style>