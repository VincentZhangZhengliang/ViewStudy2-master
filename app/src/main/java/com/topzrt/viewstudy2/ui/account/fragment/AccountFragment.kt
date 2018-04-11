package com.topzrt.viewstudy2.ui.account.fragment


import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.liaoinstan.springview.container.DefaultFooter
import com.liaoinstan.springview.container.DefaultHeader
import com.liaoinstan.springview.widget.SpringView
import com.topzrt.viewstudy2.R
import com.topzrt.viewstudy2.adapter.SoonRepayAdapter
import com.topzrt.viewstudy2.app.MyApplication
import com.topzrt.viewstudy2.base.BaseFragment
import com.topzrt.viewstudy2.network.ReApiImpl
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBalanceBean
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBasicBean
import com.topzrt.viewstudy2.ui.account.presenter.AccountPresenter
import com.topzrt.viewstudy2.ui.account.view.IAccountView
import com.topzrt.viewstudy2.ui.setting.SettingActivity
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterRepayListBean
import com.topzrt.viewstudy2.utils.ImageUtils
import com.topzrt.viewstudy2.utils.L
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_account.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileNotFoundException


/**
 * A simple [Fragment] subclass.
 */
class AccountFragment : BaseFragment(), IAccountView {

    override fun toast(msg: String) {
        fragment_accout_sv_springview.onFinishFreshAndLoad()
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    val presenter = AccountPresenter(this)

    private var dataList: MutableList<UcCenterRepayListBean> = arrayListOf()

    override fun initData() {
        if (MyApplication.instance.mUser?._Sessionkey != null && MyApplication.instance.mUser?.uid != null) {
            presenter.getData(activity, MyApplication.instance.mUser!!.uid, MyApplication.instance.mUser!!._Sessionkey)
        }
    }

    /**
     * 获取到数据后显示数据
     */
    override fun setData(data: Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>) {
        fragment_accout_sv_springview.onFinishFreshAndLoad()
        val ucCenterBean = data.first
        val hxAccountBalanceBean = data.second
        val hxAccountBasicBean = data.third
        fragment_accout_tv_account.text = ucCenterBean.data.mobile                                  //当前账户
        fragment_accout_tv_account_asset.text = ucCenterBean.data.total_money                       //丰付总资产
        fragment_accout_tv_ff_account_money.text = ucCenterBean.data.money                          //丰付账户余额
        fragment_accout_tv_ff_loading_earnings.text = ucCenterBean.data.load_earnings               //丰付累计收益
        fragment_accout_tv_load_wait_earnings.text = ucCenterBean.data.load_wait_earnings           //待收收益
        fragment_accout_tv_rj_money.text = ucCenterBean.data.rjmoney_sum                            //待收本金
        dataList.clear()
        dataList.addAll(ucCenterBean.data.repay_list)
        fragment_account_slv.adapter = SoonRepayAdapter(activity, dataList)

        if ("Y" == hxAccountBalanceBean.status) {
            fragment_accout_tv_hx_account_momey.text = hxAccountBalanceBean.data.money                  //华兴账户余额
            fragment_accout_tv_hx_locked_momey.text = hxAccountBalanceBean.data.lockMoney               //华兴冻结金额
        }
    }

    override fun initView() {}

    override fun initListener() {
        //设置按钮点击事件
        fragment_accout_iv_setting.setOnClickListener {
            startActivity(Intent(activity, SettingActivity::class.java))
        }
        //下拉刷新布局
        fragment_accout_sv_springview.header = DefaultHeader(activity)
        //上拉加载布局
        fragment_accout_sv_springview.footer = DefaultFooter(activity)
        //刷新监听
        fragment_accout_sv_springview.setListener(object : SpringView.OnFreshListener {

            override fun onLoadmore() {
                if (MyApplication.instance.mUser?._Sessionkey != null && MyApplication.instance.mUser?.uid != null) {
                    presenter.onRefresh(activity, MyApplication.instance.mUser!!.uid, MyApplication.instance.mUser!!._Sessionkey)
                }
            }

            override fun onRefresh() {
                if (MyApplication.instance.mUser?._Sessionkey != null && MyApplication.instance.mUser?.uid != null) {
                    presenter.onLoadMore(activity, MyApplication.instance.mUser!!.uid, MyApplication.instance.mUser!!._Sessionkey)
                }
            }
        })


        fragment_accout_tv_withdraw.setOnClickListener {
            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 100)
            } else {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                intent.putExtra("return-data", false)
                startActivityForResult(intent, 2)
            }
        }
    }

    /**
     *  选择相片后返回的结果处理
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            System.out.println("requestCode ：$resultCode")
            if (requestCode == 2) {
                val uri: Uri = data.data
                println(uri)
                val cr = activity.contentResolver
                try {
                    val bmp = BitmapFactory.decodeStream(cr.openInputStream(uri))
                    val realPathFromUri = ImageUtils.getRealPathFromUri(activity, uri)
                    val file = File(realPathFromUri)
                    val file2 = RequestBody.create(MediaType.parse("multipart/form-data"), file)
                    val filePart = MultipartBody.Part.createFormData("file", file.name, file2)
                    ReApiImpl.uploadPic(activity, filePart).subscribe(object : Observer<String> {
                        override fun onNext(t: String) {
                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        }

                        override fun onComplete() {
                            L.e("onComplete ++++++++++++++")
                        }

                        override fun onSubscribe(d: Disposable) {
                            L.e("onSubscribe ++++++++++++++")

                        }

                        override fun onError(e: Throwable) {
                            L.e("onError ++++++++++++++ ${e.message}")
                        }
                    })
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }

    /**
     * 权限申请
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                intent.putExtra("return-data", true)
                startActivityForResult(intent, 2)
            } else {
                Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 刷新数据
     */
    override fun refreshData(refreshData: Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>) {
        setData(refreshData)
    }

    /**
     * 加载更多数据
     */
    override fun loadMoreData(loadMoreData: Triple<UcCenterBean, HxAccountBalanceBean, HxAccountBasicBean>) {
        setData(loadMoreData)
    }

    /**
     * 加载布局
     */
    override fun getLayout(): Int {
        return R.layout.fragment_account
    }

    /**
     * 打开加载框
     */
    override fun showLoading() {
    }

    /**
     * 关闭加载框
     */
    override fun closeLoading() {
    }

}
