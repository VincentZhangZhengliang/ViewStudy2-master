package com.topzrt.viewstudy2.ui.setting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.topzrt.viewstudy2.MainActivity
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.base.BaseActivity
import com.topzrt.viewstudy2.ui.setting.bean.HxcgStatusBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean
import com.topzrt.viewstudy2.ui.setting.listener.OnLogOutListener
import com.topzrt.viewstudy2.ui.setting.presenter.SettingPresenter
import com.topzrt.viewstudy2.ui.setting.view.ISettingView
import com.topzrt.viewstudy2.view.LoadingDialog
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class SettingActivity : BaseActivity(), ISettingView {

    private val presenter: SettingPresenter = SettingPresenter(this)
    private lateinit var hxcgStatusBean: HxcgStatusBean
    private lateinit var ucCenterBean: UcCenterBean

    override fun setData(data: Pair<HxcgStatusBean, UcCenterBean>) {
        hxcgStatusBean = data.first
        ucCenterBean = data.second
        /************  丰付相关  *************/
        activity_setting_tv_ff_certification.text = if (ucCenterBean.data.credit_status == 0) "点击开通" else ucCenterBean.data.real_name
        activity_setting_tv_ff_deposit.text = if (ucCenterBean.data.credit_status == 0) "点击开通" else "已开通"
        activity_setting_rl_ff_bindcard.visibility = if (ucCenterBean.data.sumpany_start == 1) View.VISIBLE else View.GONE

        /************  华兴相关  *************/
        if (hxcgStatusBean.data.param_value == 1) {
            activity_setting_rl_hx_account.visibility = View.VISIBLE
            activity_setting_rl_hx_bindcard.visibility = View.VISIBLE
            activity_setting_rl_hx_certification.visibility = View.VISIBLE
            activity_setting_rl_hx_police_record.visibility = View.VISIBLE
        } else {
            activity_setting_rl_hx_account.visibility = View.GONE
            activity_setting_rl_hx_bindcard.visibility = View.GONE
            activity_setting_rl_hx_certification.visibility = View.GONE
            activity_setting_rl_hx_police_record.visibility = View.GONE
        }
        /************  设置相关  *************/
        activity_setting_sw_gesture.isChecked = ucCenterBean.data.gestures_status == 1
        activity_setting_rl_update_gesture.visibility = if (ucCenterBean.data.gestures_status == 1) View.VISIBLE else View.GONE

    }

    override fun toast(msg: String) {
        Toast.makeText(this@SettingActivity, msg, Toast.LENGTH_SHORT).show()
    }


    private lateinit var loadDialog: LoadingDialog

    override fun showLoading() {
    }

    override fun closeLoading() {
    }

    override fun toNextActivity(activity: AppCompatActivity, clazz: Class<*>) {
        startActivity(Intent(activity, clazz))
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        super.initData()
        if (MyApplication.instance.mUser != null) {
            presenter.getData(this@SettingActivity, MyApplication.instance.mUser!!._Sessionkey)
        }
    }

    /**
     * 处理状态栏
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        ImmersionBar.with(this).statusBarView(R.id.activity_setting_v).init()
    }

    /**
     * 初始化状态栏
     */
    override fun initView() {
        super.initView()
        toolbar_title.text = "设置与帮助"
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar_right.visibility = View.GONE
    }

    /**
     * 初始化点击监听
     */
    override fun initListener() {
        super.initListener()
        toolbar.setNavigationOnClickListener { finish() }

        /************  丰付点击相关  *************/
        //丰付实名
        activity_setting_rl_ff_certification.setOnClickListener {

            if (ucCenterBean.data.credit_status == 0) {
                //TODO:跳转到丰付开户界面
                Toast.makeText(this@SettingActivity, "跳转到丰付开户界面", Toast.LENGTH_SHORT).show()
                presenter.toNextActivity(this@SettingActivity, MainActivity::class.java)
            }

        }
        //丰付资金托管
        activity_setting_rl_ff_deposit.setOnClickListener {
            if (ucCenterBean.data.credit_status == 0) {
                //TODO:跳转到丰付开户界面
                Toast.makeText(this@SettingActivity, "跳转到丰付开户界面", Toast.LENGTH_SHORT).show()
            }
        }
        //丰付取消绑卡
        activity_setting_rl_ff_bindcard.setOnClickListener {
            //TODO:跳转到丰付取消绑卡界面
            Toast.makeText(this@SettingActivity, "跳转到丰付取消绑卡界面", Toast.LENGTH_SHORT).show()
        }

        /************  华兴点击相关  *************/
        //华兴开户状态
        activity_setting_rl_hx_account.setOnClickListener { }
        //华兴绑卡状态
        activity_setting_rl_hx_bindcard.setOnClickListener { }
        //华兴实名状态
        activity_setting_rl_hx_certification.setOnClickListener { }
        //公安备案状态
        activity_setting_rl_hx_police_record.setOnClickListener { }

        /************  密码点击相关  *************/
        //修改登录密码
        activity_setting_rl_update_pwd.setOnClickListener {
            //TODO:跳转到修改密码页面
        }
        //手势密码开关
        activity_setting_sw_gesture.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                //TODO:开启手势密码
            } else {
                //TODO:关闭手势密码
            }

        }
        //修改手势密码
        activity_setting_rl_update_gesture.setOnClickListener {
            //TODO:修改手势密码
        }

        //联系客服
        activity_setting_rl_contact_us.setOnClickListener {
            //联系客服弹框

        }
        //意见反馈
        activity_setting_rl_recommend.setOnClickListener {
            //TODO:意见反馈界面

        }
        //关于我们
        activity_setting_rl_about_us.setOnClickListener { }
        //退出登录
        activity_setting_btn_logout.setOnClickListener {
            presenter.logOut(this@SettingActivity, object : OnLogOutListener {
                override fun logOutSuccess() {
                    presenter.toNextActivity(this@SettingActivity, MainActivity::class.java)
                }
            })
        }
    }
}
