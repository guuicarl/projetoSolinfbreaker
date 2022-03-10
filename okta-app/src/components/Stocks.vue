<template>
     <div>
             <div
      class="
        bg-white
        rounded-lg
        shadow-2x1
        w-5/12
        ml-auto
        mr-auto
        mt-6
        flex-initial
        h-1/2
      "
    >
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
          flex
          justify-between
        "
      >
        TODAS AS STOCKS
        <div class="flex flex-col">
        </div>
      </header>
      <div class="p-5">
        <table class="min-w-full divide-y divide-gray-200">
          <thead class="bg-gray-50">
            <tr>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Stock
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Bid min
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Bid Max
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Ask min
              </th>
              <th
                scope="col"
                class="
                  px-6
                  py-3
                  text-left text-xs
                  font-medium
                  text-gray-500
                  uppercase
                  tracking-wider
                "
              >
                Ask Max
              </th>
            </tr>
          </thead>
          <tbody class="bg-white divide-y divide-gray-200">
            <tr v-for="stock in stocks" :key="stock">
              <td class="px-6 py-4 whitespace-nowrap" v-if="stock.ask_min != null && stock.bid_min != null">
                <div class="flex items-center">
                  <div class="ml-4">
                    <div
                      class="text-sm font-medium text-gray-900"
                      v-if="this.$root.authenticated"
                    >
                      {{ stock.stock_name }}
                    </div>
                  </div>
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap " v-if="stock.ask_min != null && stock.bid_min != null">
                <div
                  class="text-sm text-gray-900"
                  v-if="this.$root.authenticated"
                >
                  {{
                    stock.bid_min.toLocaleString("pt-br", {
                  style: "currency",
                  currency: "BRL",
                })
                  }}
                </div>
              </td>
              <td class="px-6 py-4 whitespace-nowrap" v-if="stock.ask_min != null && stock.bid_min != null">
                <span class="text-sm text-gray-900">
                  {{
                    stock.bid_max.toLocaleString("pt-br", {
                  style: "currency",
                  currency: "BRL",
                })
                  }}
                </span>
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500" v-if="stock.ask_min != null && stock.bid_min != null">
                {{
                  stock.ask_min.toLocaleString("pt-br", {
                  style: "currency",
                  currency: "BRL",
                })
                }}
              </td>
              <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-500" v-if="stock.ask_min != null && stock.bid_min != null">
                {{
                  stock.ask_max.toLocaleString("pt-br", {
                  style: "currency",
                  currency: "BRL",
                })
                }}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
     </div>
</template>

<script>
import axios from "axios";
export default {
    data: function () {
    return {
      stocks: [],
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
        console.log(`Authorization: Bearer ${accessToken}`);
        console.log("testando");
        console.log(this.claims.name);
        try {
          let response = await axios.get("http://localhost:8085/stocks", {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.stocks = response.data;
          console.log(this.stocks);
        } catch (error) {
          this.stocks = `${error}`;
        }
      }
    },
  }
}
</script>