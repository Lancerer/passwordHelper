### passwordHelper 密码管理助手

#### 密码界面

#### 卡片界面

#### 分类界面

#### 设置界面

#### 数据库
- 数据库使用GreenDao
- 表暂定：category，user

#### 主题中心第三方无缝切换实现步骤
1. 引入第三方依赖库 implementation 'skin.support:skin-support:4.0.5'
2. 在Application中初始化
```kotlin
  SkinCompatManager.withoutActivity(this)
            .addInflater(SkinAppCompatViewInflater())// 基础控件换肤初始化
            .addInflater(SkinMaterialViewInflater())// material design 控件换肤初始化[可选]
            .loadSkin()

```
3. BaseActivity中要实现一个方法
```kotlin
  /**
     * 换肤框架
     */
    override fun getDelegate(): AppCompatDelegate {
        return SkinAppCompatDelegateImpl.get(this, this);
    }

```

4. 在main文件夹下创建相应颜色的res目录，后缀加上对应的颜色eg: res-black,res-pink,res文件夹下创建values文件夹在创建colors文件，
下面定义相应颜色，前缀一致，后缀就是相应的颜色

5. 具体调用
```kotlin

   activity?.let {
                    MaterialDialog(it).show {
                        title(R.string.setting_theme_hint)
                        listItems(R.array.themeName) { dialog, index, text ->
                            if (text == Constant.DEFAULT) {
                                SkinCompatManager.getInstance().restoreDefaultTheme()
                            } else {
                                SkinCompatManager.getInstance().loadSkin(
                                    text.toString(),
                                    SkinCompatManager.SKIN_LOADER_STRATEGY_BUILD_IN
                                )
                            }
                        }
                    }
                }
```

6. 注意在设置控件的颜色的时候，     android:background="@color/colorPrimary" ，之后框架会自动替换颜色


#### 项目中的一些问题
- spinner 默认选中
- lateinit var 延迟加载问题
- recyclerView Decoration 分割线问题