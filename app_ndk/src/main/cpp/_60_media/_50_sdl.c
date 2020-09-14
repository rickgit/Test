//
// Created by anshu-pc on 2020/9/10.
//
#include <SDL.h>
#include <unistd.h>

void test_000_SimpleWindow();

void test_001_render();

void test_002_event();

void test_003_texture();

int SDL_main(int argc, char *argv[]) {
    // sdl 入口
//    test_000_SimpleWindow();
//    test_001_render();
//    test_002_event();
    test_003_texture();

}

void test_003_texture() {
    SDL_Init(SDL_INIT_VIDEO);
    SDL_Window *pWindow
            = SDL_CreateWindow("SDL2 window", SDL_WINDOWPOS_UNDEFINED,
                               SDL_WINDOWPOS_UNDEFINED, 300, 700,
                               SDL_WINDOW_SHOWN);
    if (!pWindow) {
        //print err
        goto exit_release;
    }
    SDL_Renderer *pRenderer = SDL_CreateRenderer(pWindow, -1, 0);
    SDL_RenderClear(pRenderer);
    SDL_SetRenderDrawColor(pRenderer, 255, 0, 0, 0);
    SDL_RenderPresent(pRenderer);

    int quit = -1;
    SDL_Event  event;

    SDL_Texture *pTexture = SDL_CreateTexture(pRenderer,SDL_PIXELFORMAT_RGB888,
                SDL_TEXTUREACCESS_TARGET,480,500);
    if (!pTexture){
        goto _render_release;
    }
    do {
//        SDL_WaitEvent(&event);
        SDL_PollEvent(&event);
        switch (event.type){
            case SDL_QUIT:
                quit=0;
                break;
            default:
                SDL_Log("SDL event %d",event.type);
        }
        SDL_Rect rect;
        rect.w=30;
        rect.h=30;
        rect.x=rand()%480;
        rect.y=rand()%500;
        SDL_SetRenderTarget(pRenderer,pTexture);
        SDL_SetRenderDrawColor(pRenderer,0,0,0,0);
        SDL_RenderClear(pRenderer);

        SDL_RenderDrawRect(pRenderer,&rect);
        SDL_SetRenderDrawColor(pRenderer,255,0,0,0);
        SDL_RenderFillRect(pRenderer,&rect);

        SDL_SetRenderTarget(pRenderer,NULL);
        SDL_RenderCopy(pRenderer,pTexture,NULL,NULL);

        SDL_RenderPresent(pRenderer);
    } while (quit);
    _render_release:
    SDL_DestroyRenderer(pRenderer);
    SDL_DestroyWindow(pWindow);
    exit_release:
    SDL_Quit();
}

void test_002_event() {
    SDL_Init(SDL_INIT_VIDEO);
    SDL_Window *pWindow
            = SDL_CreateWindow("SDL2 window", SDL_WINDOWPOS_UNDEFINED,
                               SDL_WINDOWPOS_UNDEFINED, 300, 700,
                               SDL_WINDOW_RESIZABLE | SDL_WINDOW_FULLSCREEN | SDL_WINDOW_OPENGL);
    if (!pWindow) {
        //print err
        goto exit_release;
    }
    SDL_Renderer *pRenderer = SDL_CreateRenderer(pWindow, -1, 0);

    int quit = -1;
    SDL_Event  event;
    do {
        SDL_WaitEvent(&event);
        switch (event.type){
            case SDL_QUIT:
                quit=0;
                break;
            default:
                SDL_Log("SDL event %d",event.type);
        }
        SDL_RenderClear(pRenderer);
        SDL_SetRenderDrawColor(pRenderer, 255, 0, 0, 0);
        SDL_RenderPresent(pRenderer);
    } while (quit);
    SDL_DestroyRenderer(pRenderer);
    SDL_DestroyWindow(pWindow);
    exit_release:
    SDL_Quit();
}

void test_001_render() {
    SDL_Init(SDL_INIT_VIDEO);
    SDL_Window *pWindow
            = SDL_CreateWindow("SDL2 window", SDL_WINDOWPOS_UNDEFINED,
                               SDL_WINDOWPOS_UNDEFINED, 300, 700,
                               SDL_WINDOW_RESIZABLE | SDL_WINDOW_FULLSCREEN | SDL_WINDOW_OPENGL);
    if (!pWindow) {
        //print err
        goto exit_release;
    }
    SDL_Renderer *pRenderer = SDL_CreateRenderer(pWindow, -1, 0);

    while (1) {
        SDL_RenderClear(pRenderer);
        SDL_SetRenderDrawColor(pRenderer, 255, 0, 0, 0);
        SDL_RenderPresent(pRenderer);
        SDL_Delay(40);
    }
    SDL_DestroyRenderer(pRenderer);
    SDL_DestroyWindow(pWindow);
    exit_release:
    SDL_Quit();
}

void test_000_SimpleWindow() {
    SDL_Init(SDL_INIT_VIDEO);
    SDL_Window *pWindow
            = SDL_CreateWindow("SDL2 window", SDL_WINDOWPOS_UNDEFINED,
                               SDL_WINDOWPOS_UNDEFINED, 300, 700,
                               SDL_WINDOW_SHOWN);
    if (!pWindow) {
        //print err
        goto exit_release;
    }
    SDL_DestroyWindow(pWindow);
    exit_release:
    SDL_Quit();
}
