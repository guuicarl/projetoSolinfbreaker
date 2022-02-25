<template>
  <div class="py-40 md:w-auto" >
    <!-- card vem aqui -->
    <div class="bg-white rounded-lg shadow-2x1 w-1/2 justify-center ml-auto mr-auto">
      <!-- header -->
      <header
        class="
          bg-gray-800
          rounded-t-lg
          py-3
          px-8
          text-xl
          font-extrabold
          text-gray-100
        "
      >
        CRIAR ORDEM
      </header>
      <div class="p-6">
        <div class="mt-20 sm:mt-0">
          <div class="mt-5 md:mt-0 md:col-span-3">
            <!-- <form @submit.prevent="criarOrdem()"> -->
              <div class="px-4 py-5 bg-white sm:p-6">
                <div class="grid grid-cols-6 gap-6">
                  <div class="col-span-6">
                    <label
                      for="country"
                      class="block text-sm font-medium text-gray-700"
                      >Nome da stock:
                    </label>
                    <select
                      id="country"
                      name="country"
                      autocomplete="country-name"
                      class="
                        mt-1
                        block
                        w-full
                        py-2
                        px-3
                        border border-gray-700
                        bg-white
                        rounded-md
                        shadow-sm
                        focus:outline-none
                        focus:ring-indigo-500
                        focus:border-indigo-500
                        sm:text-sm
                      "
                      v-model="name"
                      @click="getMoeda"
                    >
                      <option selected>---- Selecione uma stock ----</option>
                      <option v-for="stock in stocksName" :key="stock">{{stock.stock_name}}</option>
                    </select>
                  </div>

                  <div class="col-span-6">
                    <label
                      for="country"
                      class="block text-sm font-medium text-gray-700"
                      >Tipo da transação
                    </label>
                    <select
                      id="country"
                      name="country"
                      autocomplete="country-name"
                      class="
                        mt-1
                        block
                        w-full
                        py-2
                        px-3
                        border border-gray-700
                        bg-white
                        rounded-md
                        shadow-sm
                        focus:outline-none
                        focus:ring-indigo-500
                        focus:border-indigo-500
                        sm:text-sm
                      "
                      v-model="type"
                    >
                      <option>---- Selecione o tipo ----</option>
                      <option value="0">Compra</option>
                      <option value="1">Venda</option>
                    </select>
                  </div>

                  <div class="col-span-6">
                    <label
                      for="last-name"
                      class="block text-sm font-medium text-gray-700"
                      >Preço da ordem: 
                    </label>
                    <input
                      type="text"
                      name="last-name"
                      id="last-name"
                      autocomplete="family-name"
                      class="
                        mt-1
                        focus:ring-indigo-500 focus:border-indigo-500
                        block
                        w-full
                        shadow-sm
                        lg:text-lg
                        border border-gray-500
                        rounded-md
                      "
                      v-model="bid"
                      @mouseleave="calcTotal"
                    />
                    
                  </div>

                  <div class="col-span-6">
                    <label
                      for="email-address"
                      class="block text-sm font-medium text-gray-700"
                      >Volume:
                    </label>
                    <input
                      type="text"
                      name="email-address"
                      id="email-address"
                      autocomplete="email"
                      class="
                        mt-1
                        focus:ring-indigo-500 focus:border-indigo-500
                        block
                        w-full
                        shadow-sm
                        lg:text-lg
                        border border-gray-500
                        rounded-md
                      "
                      v-model="volume"
                      @mouseleave="calcTotal"
                    />
                  </div>

                  <div class="col-span-6">
                    <label
                      for="street-address"
                      class="block text-sm font-medium text-gray-700"
                      >Total da ordem:
                    </label>
                    <input
                      type="text"
                      name="street-address"
                      id="street-address"
                      autocomplete="street-address"
                      class="
                        mt-1
                        focus:ring-indigo-500 focus:border-indigo-500
                        block
                        w-full
                        shadow-sm
                        lg:text-lg
                        border border-gray-500
                        rounded-md
                      "
                      v-model="total"
                    />
                  </div>
                </div>
              </div>
              <div class="px-4 py-3 text-right sm:px-6">
                <button
                  type="submit"
                  class="
                    inline-flex
                    justify-center
                    py-2
                    px-4
                    border border-transparent
                    shadow-sm
                    text-sm
                    font-medium
                    rounded-md
                    text-white
                    bg-gray-700
                    hover:bg-gray-600
                    focus:outline-none
                    focus:ring-2
                    focus:ring-offset-2
                    focus:ring-indigo-500
                  "
                  @click="criarOrdem()"
                >
                  CRIAR ORDEM
                </button>
              </div>
              <!-- </form> -->
          </div>
        </div>
      </div>
      <div v-if="sucesso" class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative" role="alert">
          <span class="absolute top-0 bottom-0 right-0 px-4 py-3">
    <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor" role="button" @click="sucesso = false">
  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4M7.835 4.697a3.42 3.42 0 001.946-.806 3.42 3.42 0 014.438 0 3.42 3.42 0 001.946.806 3.42 3.42 0 013.138 3.138 3.42 3.42 0 00.806 1.946 3.42 3.42 0 010 4.438 3.42 3.42 0 00-.806 1.946 3.42 3.42 0 01-3.138 3.138 3.42 3.42 0 00-1.946.806 3.42 3.42 0 01-4.438 0 3.42 3.42 0 00-1.946-.806 3.42 3.42 0 01-3.138-3.138 3.42 3.42 0 00-.806-1.946 3.42 3.42 0 010-4.438 3.42 3.42 0 00.806-1.946 3.42 3.42 0 013.138-3.138z" />
</svg>
  </span>
  <strong class="font-bold">Ordem cadastrada com sucesso!</strong>
</div>

<div v-if="erro" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative" role="alert">
  <strong class="font-bold">Ordem não cadastrada! Verifique os campos e se possui saldo suficiente</strong>
  <span class="absolute top-0 bottom-0 right-0 px-4 py-3">
    <svg class="fill-current h-6 w-6 text-red-500" role="button" @click="erro = false" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><title>Close</title><path d="M14.348 14.849a1.2 1.2 0 0 1-1.697 0L10 11.819l-2.651 3.029a1.2 1.2 0 1 1-1.697-1.697l2.758-3.15-2.759-3.152a1.2 1.2 0 1 1 1.697-1.697L10 8.183l2.651-3.031a1.2 1.2 0 1 1 1.697 1.697l-2.758 3.152 2.758 3.15a1.2 1.2 0 0 1 0 1.698z"/></svg>
  </span>
</div>
    </div>
    
  </div>
</template>

<script>
import axios from "axios";
import {VMoney} from 'v-money'


export default {
  data: function () {
    return {
      stocksName: [],
      sucesso: false,
      erro:false,
      name: '---- Selecione uma stock ----',
      bid: '',
      volume: '',
      total: '',
      type: '---- Selecione o tipo ----',
      teste: [],
      id: 0,
      dinheiro: '',
      money: {
          decimal: ',',
          thousands: '.',
          prefix: 'R$ ',
          precision: 0,
          masked: false /* doesn't work with directive */
        }
    };
  },
  created() {
    this.setup();
  },
  methods: {
    async setup() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        try {
          let response = await axios.get("http://localhost:8085/stocks", {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.stocksName = response.data;
          console.log('teste')
        } catch (error) {
          this.stocksName = `${error}`;
        }
        try {
        let response = await axios.get(
          `http://localhost:8082/u/${this.claims.name}`,

          {
            headers: { Authorization: "Bearer " + accessToken },
          }
        );
        this.id = response.data;
      } catch (error) {
        this.users = `${error}`;
      }  
      }
    },
    async getMoeda() {
      console.log("to aquiiiiiiiiii")
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        try {
          let response = await axios.get(`http://localhost:8085/${this.name}`, {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.teste = response.data;
          console.log("id: " + this.teste[0].id + " Symbol: " + this.teste[0].stock_symbol + " Nome: " + this.name + " Volume: " + this.volume  + " Preço: " + this.bid, "tipo: " + this.type) 
        } catch (error) {
          this.walletUser = `${error}`;
        }
      }
    },
    async criarOrdem() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        // if(this.volume * this.bid <= this.dinheiro){
        try {
          await axios.post(`http://localhost:8082/orders`, 
          {id_user: this.id, id_stock: this.teste[0].id, stock_symbol: this.teste[0].stock_symbol, stock_name: this.name, volume: this.volume, price: this.bid, type: this.type, status: 1, remaining_value: this.volume},
          {
            headers: { Authorization: "Bearer " + accessToken },
          })
          .then(() => {
            console.log("criou")
            this.sucesso = true
            this.name= '---- Selecione uma stock ----'
            this.bid = ''
            this.volume= ''
            this.type= '---- Selecione o tipo ----'

          })
          console.log("id: " + this.teste[0].id + " Symbol: " + this.teste[0].stock_symbol + " Nome: " + this.name + " Volume: " + this.volume  + " Preço: " + this.bid) 
        } catch (error) {
          console.log(error);
        }
        // } else {
        //   this.erro = true
        //   this.sucesso = false
        // }
      }
    },
    async calcTotal(){
      let calc = this.volume * this.bid
      this.total = calc.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'})
    }
  },
        directives: {money: VMoney}
};
</script>