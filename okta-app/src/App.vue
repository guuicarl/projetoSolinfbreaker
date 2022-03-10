<template>
<div class="bg-indigo-100 min-h-screen">
  <div id="app2">
    <nav class="bg-gray-800">
    <div class="max-w-6x1">
      <div class="flex justify-between">
        <!-- logo -->
        <div>
          <a href="/" class="flex items-center py-4 px-2 text-gray-700 no-underline	"> <!--Onde eu coloco a cor dos links -->
            <svg
              xmlns="http://www.w3.org/2000/svg"
              class="h-12 w-10 mr-2 text-yellow-400"
              viewBox="0 0 20 20"
              fill="currentColor"
            >
              <path
                fill-rule="evenodd"
                d="M12 7a1 1 0 110-2h5a1 1 0 011 1v5a1 1 0 11-2 0V8.414l-4.293 4.293a1 1 0 01-1.414 0L8 10.414l-4.293 4.293a1 1 0 01-1.414-1.414l5-5a1 1 0 011.414 0L11 10.586 14.586 7H12z"
                clip-rule="evenodd"
              />
            </svg>
            <span class="font-bold text-gray-100 hover:text-gray-400 text-lg">SolinfBroker</span>
          </a>
        </div>
        <!-- secondary nav -->
        <div class="flex items-center space-x-1 mr-9">
          <a href="/" class="py-5 px-3 text-gray-100 hover:text-gray-400 no-underline" v-if="authenticated">Dashboard</a>
          <a href="/teste" class="py-5 px-3 text-gray-100 hover:text-gray-400 no-underline" v-if="authenticated">Compra/Venda</a>
          <a href="/carteira" class="py-5 px-3 text-gray-100 hover:text-gray-400 no-underline" v-if="authenticated">Carteira</a>
          <a href="" class="py-3 px-3 bg-yellow-400 hover:bg-yellow-300 text-gray-100 hover:text-yellow-800 rounded transition duration-300 no-underline" v-on:click="logout()" v-if="authenticated">Logout</a>
        </div>
      </div>
    </div>
  </nav>
    <div id="content">
      <router-view />
    </div>
  </div>
  <footer class="text-gray-100 bg-gray-800 fixed inset-x-0 bottom-0">
        <div class="max-w-3xl mx-auto py-5">
    
    
            <hr class="h-px mt-6 bg-gray-700 border-none">
    
            <div class="flex flex-col items-center justify-between mt-6 md:flex-row">
                <div>
                    <a href="#" class="text-xl font-bold text-gray-100 hover:text-gray-400">SolinfBroker</a>
                </div>
    
                <div class="flex mt-4 md:m-0">
                    <div class="-mx-4">
                        <div class="px-4 text-sm text-gray-100 font-medium hover:text-gray-400">&copy; 2022, all rights reserved.</div>
                    </div>
                </div>
            </div>
        </div>
    </footer>   
</div>
</template>

<script>
import "./assets/tailwind.css";
export default {
  name: "app",
  data: function () {
    return { authenticated: false };
  },
  async created() {
    await this.isAuthenticated();
    this.$auth.authStateManager.subscribe(this.isAuthenticated);
  },
  watch: {
    // Everytime the route changes, check for auth status
    $route: "isAuthenticated",
  },
  methods: {
    async isAuthenticated() {
      this.authenticated = await this.$auth.isAuthenticated();
    },
    async logout() {
      await this.$auth.signOut();
    },
  },
};
</script>

<style>

</style>