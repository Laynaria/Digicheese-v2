import { createRouter } from '@tanstack/react-router'
import { Route as rootRoute } from './routes/__root'
import { Route as indexRoute } from './routes/index'
import { Route as usersRoute } from './routes/users'
import { queryClient } from './lib/query-client'

const routeTree = rootRoute.addChildren([indexRoute, usersRoute])

export const router = createRouter({
    routeTree,
    context: { queryClient },
})

declare module '@tanstack/react-router' {
    interface Register {
        router: typeof router
    }
}