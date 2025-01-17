import axios, {
    AxiosRequestConfig,
    AxiosResponse,
} from "axios";
import { ElMessage, ElLoading } from "element-plus";

const state = {
    ok: 0,//请求成功状态码
    401: "ERR_BAD_REQUEST"
};
//返回数据规则
export interface IResponseData<T> {
    status: number;
    message?: string;
    data: T;
    code: string;
}

//请求默认配置规则
type TOption = {
    baseURL: string;
    timeout: number;
};

//默认配置
const config = {
    baseURL: "",
    timeout: 30 * 1000,
    withCredentials: true,
};

let loading: any = null;

class Http {
    service: any;
    constructor(config: TOption) {
        //实例化请求配置
        this.service = axios.create(config);

        //请求拦截
        this.service.interceptors.request.use(
            (config: AxiosRequestConfig) => {
                loading = ElLoading.service({ fullscreen: true, text: '加载中...' });
                // console.log("token===",token.value);
                return config;
            },
            (error: any) => {
                loading.close();
                return Promise.reject(error);
            }
        );

        //响应拦截
        this.service.interceptors.response.use(
            (response: AxiosResponse) => {
                loading.close();
                //console.log(response)   
                const mydata = response.data;
                const { code } = mydata;

                if (code == undefined) {
                    //如果没有返回状态码，直接返回数据，针对于返回数据为blob类型    
                    return response;
                } else if (code !== 0) {
                    ElMessage.error(mydata.message);
                    return Promise.reject(mydata);
                }
                // code == 0 的时候，提取我们只关注的Api数据data
                //console.log(mydata.data);
                return mydata.data;
            },

        );
    }
    //url：访问的地址 params：作为URL参数的参数对象  data 封装的发送数据
    get<T>(url: string, params?: object, data = {}): Promise<IResponseData<T>> {
        return this.service.get(url, { params, ...data });
    }

    post<T>(url: string, params?: object, data = {}): Promise<IResponseData<T>> {
        return this.service.post(url, params, data);
    }

    put<T>(url: string, params?: object, data = {}): Promise<IResponseData<T>> {
        return this.service.put(url, params, data);
    }
    patch<T>(url: string, params?: object, data = {}): Promise<IResponseData<T>> {
        return this.service.patch(url, params, data);
    }

    delete<T>(
        url: string,
        params?: object,
        data = {}
    ): Promise<IResponseData<T>> {
        return this.service.delete(url, { params, ...data });
    }
}
export default new Http(config);