<template>
  <section>
    <notifications group="success" position="top left"
                   class="success"
                   width="300px" :duration="4000">
      <template slot="body" slot-scope="props">
        <div>
          <span class="notification-icon">
            <h2><i class="fa fa-check-square"></i></h2>
          </span>
          <a class="title">
            {{props.item.title}}
          </a>
          <a class="close" @click="props.close">
            <i class="fa fa-fw fa-close"></i>
          </a>
          <div v-html="props.item.text" class="notification-content">
          </div>
        </div>
      </template>
    </notifications>

    <notifications group="warning" position="top left"
                   class="warning"
                   width="300px" :duration="4000">
      <template slot="body" slot-scope="props">
        <div>
          <span class="notification-icon">
            <h2><i class="fa fa-exclamation-triangle"></i></h2>
          </span>
          <a class="title">
            {{props.item.title}}
          </a>
          <a class="close" @click="props.close">
            <i class="fa fa-fw fa-close"></i>
          </a>
          <div v-html="props.item.text" class="notification-content">
          </div>
        </div>
      </template>
    </notifications>

    <notifications group="error" position="top left"
                   class="error"
                   width="300px" :duration="4000">
      <template slot="body" slot-scope="props">
        <div>
          <span class="notification-icon">
            <h2><i class="fa fa-times-circle"></i></h2>
          </span>
          <a class="title">
            {{props.item.title}}
          </a>
          <a class="close" @click="props.close">
            <i class="fa fa-fw fa-close"></i>
          </a>
          <div v-html="props.item.text" class="notification-content">
          </div>
        </div>
      </template>
    </notifications>
  </section>
</template>
