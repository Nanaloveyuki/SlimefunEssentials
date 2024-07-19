## SlimefunEssentials
Slimefun Essentials 是一个 Minecraft 模组，它致力于为其他客户端模组添加了一些额外的SLimefun支持.

English: [README](https://github.com/JustAHuman-xD/SlimefunEssentials#readme)

---

### 有什么用?:
- 给EMI, REI, JEI增加了Slimefun的配方 
- 给Slimefun的物品和方块增加自定义材质的支持 (需要 More Block Predicates 在客户端, 且 [Slimefun Server Essentials](https://github.com/JustAHuman-xD/SlimefunServerEssentials/releases) (插件)需要安装在服务端)
- 添加了Slimefun方块对玉(Jade)的兼容 (需要 Jade 在客户端, 且 Slimefun Server Essentials 需要安装在服务端)
- 即将到来的更多功能™

### 下载
- [Github](https://github.com/JustAHuman-xD/SlimefunEssentials/releases)
- [Modrinth](https://modrinth.com/mod/slimefun-essentials)
- [Curseforge](https://www.curseforge.com/minecraft/mc-mods/slimefun-essentials)

### 依赖项
- Mod Menu (可选)
- Cloth Config API (可选)
- More Block Predicates (可选)
- Jade (可选)
- 下列之一: (可选)
  - EMI
  - JEI
  - REI

### 配置:
- "block_features" (方块功能)
  - Slimefun Essentials 是否应支持  Jade 和 自定义材质(MBP)
  - 需要 More Block Predicates (因为需要自定义材质)
  - 需要 Jade (因为需要与 Jade 整合兼容)
  - 需要 Slimefun Server Essentials 安装在服务器上 (任何功能都需要)
- "recipe_features" (配方功能)
  - Slimefun Essentials是否应该支持并加载Slimefun配方到EMI, JEI, 或 REI
  - 需要 EMI, JEI, or REI
- "auto_toggle_addons" (自动切换插件)
  - 当加入安装了Slimefun Server Essentials的服务器时, Slimefun Essentials是否应尝试自动打开或关闭加载项
- "addons"
  - 一个列表, 列出被支持的插件
  - 只有当所需的**所有功能项**被加载时才能使用