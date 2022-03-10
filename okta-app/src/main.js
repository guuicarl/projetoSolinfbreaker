import 'devextreme/dist/css/dx.common.css';
import 'devextreme/dist/css/dx.light.css';
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import OktaVue from '@okta/okta-vue'
import { oktaAuth } from './okta';
import './assets/tailwind.css'

// linhas alteradas
// import VueSweetalert2 from 'vue-sweetalert2';
// import 'sweetalert2/dist/sweetalert2.min.css';

// app.use(VueSweetalert2);

// ate emcima
createApp(App)
    .use(router)
    .use(OktaVue, {
        oktaAuth,
        onAuthRequired: () => {
            router.push('/login')
        },
        onAuthResume: () => {
            router.push('/login')
        },
    })
    .mount('#app')