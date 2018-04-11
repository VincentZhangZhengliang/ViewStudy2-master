package com.topzrt.viewstudy2.network

import com.topzrt.viewstudy2.bean.*
import com.topzrt.viewstudy2.ui.BbinIncome.bean.BbinIncomeBean
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBalanceBean
import com.topzrt.viewstudy2.ui.account.bean.HxAccountBasicBean
import com.topzrt.viewstudy2.ui.dochange.bean.DoChangeRecordBean
import com.topzrt.viewstudy2.ui.finance_activity.bean.CatTypeListBean
import com.topzrt.viewstudy2.ui.finance_activity.bean.DealsListBean
import com.topzrt.viewstudy2.ui.integralmall.bean.DoChangeBean
import com.topzrt.viewstudy2.ui.integralmall.bean.IntegralMallBean
import com.topzrt.viewstudy2.ui.setting.bean.HxcgStatusBean
import com.topzrt.viewstudy2.ui.setting.bean.UcCenterBean
import com.topzrt.viewstudy2.ui.signup.bean.ContinuousSignUpBean
import com.topzrt.viewstudy2.ui.signup.bean.IntegralListBean
import com.topzrt.viewstudy2.ui.signup.bean.SignUpBean
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.*
import java.util.*

/**
 * Created by Vincent;
 * Created on 2018/1/9;
 * DSC:
 */
interface ReApi {

    @POST("/login") @FormUrlEncoded fun login(@FieldMap map: LinkedHashMap<String, String>): Observable<LoginBean>

    @POST("/banner") @FormUrlEncoded fun banner(@FieldMap map: LinkedHashMap<String, String>): Observable<HomeBannerBean>

    @POST("/qidongye") @FormUrlEncoded fun qiDongYe(@FieldMap map: LinkedHashMap<String, String>): Observable<QiDongYeBean>

    @POST("/licaiRecommendation") @FormUrlEncoded fun licaiRecommendation(@FieldMap map: LinkedHashMap<String, String>): Observable<RecommendationBean>

    @POST("/send_register_code") @FormUrlEncoded fun sendRegisterCode(@FieldMap map: LinkedHashMap<String, String>): Observable<VerifyCodeBean>

    @POST("/register") @FormUrlEncoded fun register(@FieldMap map: LinkedHashMap<String, String>): Observable<RegisterBean>

    @GET fun getIp(): Observable<String>

    @POST("/uc_center") @FormUrlEncoded fun ucCenter(@FieldMap map: LinkedHashMap<String, String>): Observable<UcCenterBean>

    //华兴存管开关
    @POST("/get_hxcg_status") @FormUrlEncoded fun getHxcgStatus(@FieldMap map: LinkedHashMap<String, String>): Observable<HxcgStatusBean>

    //积分商城
    @POST("/score_red_bag") @FormUrlEncoded fun scoreRedbag(@FieldMap map: LinkedHashMap<String, String>): Observable<IntegralMallBean>

    //积分兑换
    @POST("/dochange") @FormUrlEncoded fun doChange(@FieldMap map: LinkedHashMap<String, String>): Observable<DoChangeBean>

    //积分兑换记录
    @POST("/recordchange") @FormUrlEncoded fun recordChange(@FieldMap map: LinkedHashMap<String, String>): Observable<DoChangeRecordBean>

    //积分日历
    @POST("/integral_list") @FormUrlEncoded fun integralList(@FieldMap map: LinkedHashMap<String, String>): Observable<IntegralListBean>

    //签到
    @POST("/uc_score_signin") @FormUrlEncoded fun ucScoreSignIn(@FieldMap map: LinkedHashMap<String, String>): Observable<SignUpBean>

    //连续签到
    @POST("/receive") @FormUrlEncoded fun receive(@FieldMap map: LinkedHashMap<String, String>): Observable<ContinuousSignUpBean>

    //体验标收益
    @POST("/show_new_deal") @FormUrlEncoded fun showNewDeal(@FieldMap map: LinkedHashMap<String, String>): Observable<BbinIncomeBean>

    //标的分类和类型
    @POST("/catTypeList") @FormUrlEncoded fun catTypeList(@FieldMap map: LinkedHashMap<String, String>): Observable<CatTypeListBean>

    //理财列表
    @POST("/dealslist") @FormUrlEncoded fun dealslist(@FieldMap map: LinkedHashMap<String, String>): Observable<DealsListBean>

    //获取H5
    //1-公司简介 2-安全保障3-补签说明4-兑换须知5-专家顾问6-风险提示书7-安全保障8-理财经理9-管理团队10-充值注意事项11-提现注意事项
    @POST("/get_h5") @FormUrlEncoded fun get_h5(@FieldMap map: LinkedHashMap<String, String>): Observable<H5Bean>

    /*******************************    华兴相关   *****************************/
    //华兴账户相关
    @GET("/terminal/user/accountbasic") fun hxAccountBasic(@Query("userid") userid: String, @Query("appid") appid: String, @Query("sign") sign: String): Observable<HxAccountBasicBean>

    //华兴账户余额
    @GET("/terminal/user/accountbalance") fun hxAccountBalance(@Query("userid") userid: String, @Query("appid") appid: String, @Query("sign") sign: String): Observable<HxAccountBalanceBean>

    @POST("/upload_files") @Multipart fun uploadPic(@PartMap map: LinkedHashMap<String, String>, @Part file: MultipartBody.Part): Observable<String>
//    @POST("/upload_files") @Multipart fun uploadPic(@FieldMap map: LinkedHashMap<String, String>, @Part file: MultipartBody.Part): Observable<Unit>


}
