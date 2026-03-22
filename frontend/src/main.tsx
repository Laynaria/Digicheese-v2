import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { RouterProvider } from '@tanstack/react-router'
import { QueryClientProvider } from '@tanstack/react-query'
import { ReactQueryDevtools } from '@tanstack/react-query-devtools'
import { TanStackRouterDevtools } from '@tanstack/router-devtools'
import { router } from './router'
import { queryClient } from './lib/query-client'
import './index.css'

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <QueryClientProvider client={queryClient}>
            <RouterProvider router={router} />
            {import.meta.env.DEV && (
                <>
                    <ReactQueryDevtools initialIsOpen={false} />
                    <TanStackRouterDevtools router={router} />
                </>
            )}
        </QueryClientProvider>
    </StrictMode>
)